package com.dd.manager.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dd.common.utils.date.DateStyle;
import com.dd.common.utils.date.DateUtils;
import com.dd.manager.modules.log.dao.RedpackLogDao;
import com.dd.manager.modules.log.entity.RedpackLogEntity;
import com.dd.manager.modules.log.entity.SendLogEntity;
import com.dd.manager.modules.log.service.CheckLogService;
import com.dd.manager.modules.log.service.RedpackLogService;
import com.dd.manager.modules.log.service.SendLogService;
import com.dd.manager.modules.log.vo.RedpackLogVO;
import com.dd.manager.modules.product.entity.ProductCodeEntity;
import com.dd.manager.modules.product.service.ProductCodeService;
import com.dd.manager.web.config.profile.RedPackProfile;
import com.dd.manager.web.exception.BaseException;
import com.dd.manager.web.exception.message.ErrorInfo;
import com.dd.manager.web.result.R;
import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.request.WxPaySendRedpackRequest;
import com.github.binarywang.wxpay.bean.result.WxPayMicropayResult;
import com.github.binarywang.wxpay.bean.result.WxPaySendRedpackResult;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service("redpackLogService")
public class RedpackLogServiceImpl extends ServiceImpl<RedpackLogDao, RedpackLogEntity> implements RedpackLogService {

    private static final Logger loger = LoggerFactory.getLogger(RedpackLogServiceImpl.class);

    @Autowired
    RedpackLogDao redpackLogDao;

    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private CheckLogService checkLogService;
    @Autowired
    private ProductCodeService productCodeService;
    @Autowired
    private SendLogService sendLogService;
    @Autowired
    private RedPackProfile redpackProfile;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized R send(RedpackLogEntity log) {

        //校验订单是否为未发送
        RedpackLogEntity cur_entity = redpackLogDao.selectOne(
                new QueryWrapper<RedpackLogEntity>().eq("product_code", log.getProductCode()));
        if (cur_entity != null) {
            throw new BaseException(ErrorInfo.REDPACK_HAS_BEEN_RECEVIED);
        } else {
            cur_entity = new RedpackLogEntity();
            cur_entity.setCreateTime(new Date());
        }
        //生成10位随机数，并入库
        String genRanNum = genRanNum();
        Date now = new Date();
        int count = redpackLogDao.checkRandom(now, genRanNum);
        while (count > 0) {
            genRanNum = genRanNum();
            now = new Date();
            count = redpackLogDao.checkRandom(now, genRanNum);
        }
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date expire = DateUtils.getNextDay(c.getTime(), 1);
        redpackLogDao.insertgenRanNum(expire, genRanNum);
        cur_entity.setSendTime(now);
        WxPayConfig config = wxPayService.getConfig();
        String mchBillNo = DateUtils.dateToString(new Date(), DateStyle.YYYYMMDDHHMMSS) + genRanNum;
        cur_entity.setOrderNo(mchBillNo);
        int totalAmount = this.getTotalAmount();
        if ("test".equals(redpackProfile.getProfile()) || "dev".equals(redpackProfile.getProfile())) {
            totalAmount = 1;
        }
        cur_entity.setTotalAmount(totalAmount);
        cur_entity.setOpenId(log.getOpenId());
        cur_entity.setProductCode(log.getProductCode());
        cur_entity.setProductId(log.getProductId());
        cur_entity.setStatus(2);
        cur_entity.setLatitude(log.getLatitude());
        cur_entity.setLongitude(log.getLongitude());
        this.saveOrUpdate(cur_entity);

        try {
            WxPaySendRedpackRequest request = WxPaySendRedpackRequest.newBuilder()
                    .sceneId("PRODUCT_1")
                    .actName("红包活动")
                    .wxAppid(config.getAppId())
                    .sendName("中商国际贸易有限公司")
                    .reOpenid(cur_entity.getOpenId())
                    .clientIp("47.52.19.181")
                    .mchBillNo(mchBillNo)
                    .totalAmount(totalAmount)
                    .totalNum(1)
                    .wishing("恭喜获得红包")
                    .build();
            WxPaySendRedpackResult sendRedpack = wxPayService.sendRedpack(request);
            String resultCode = sendRedpack.getResultCode();
            if (!"SUCCESS".equals(resultCode)) {
                redpackLogDao.deleteById(cur_entity.getId());
                loger.error("发送红包有误！openId:{},金额：{},时间：{},原因：{}", log.getOpenId(),
                        totalAmount, DateUtils.dateToString(log.getCreateTime(), "yyyyMMdd HHmmss"),
                        sendRedpack.getReturnMsg());
                return R.error("发送红包失败，请联系工作人员！");
            } else {
                BigDecimal amount = new BigDecimal(totalAmount);
                cur_entity.setStatus(3);
                this.saveOrUpdate(cur_entity);
                return R.ok("红包发送成功！金额：" + totalAmount + ",单位：分").put("amount", amount.divide(new BigDecimal(100)).toString() + "元");
            }
        } catch (Exception e) {
            loger.error(e.getMessage(), e);
            redpackLogDao.deleteById(cur_entity.getId());
            return R.error("发送红包失败，请联系工作人员！");
        }
    }

    private int getTotalAmount() {
        int nextInt = RandomUtils.nextInt(0, 100);
        int totalAmount = 0;
        if (nextInt >= 80) {
            totalAmount = 88;
        } else if (nextInt >= 40) {
            totalAmount = 168;
        } else {
            totalAmount = 188;
        }
        return totalAmount;
    }

    private String genRanNum() {
        StringBuilder sb = new StringBuilder();
        int a[] = new int[10];

        for (int i = 0; i < a.length; i++) {

            a[i] = (int) (10 * (Math.random()));

            sb.append(a[i]);
        }
        return sb.toString();
    }

    @Override
    public List<RedpackLogEntity> selectPage(Map<String, Object> param) {
        return redpackLogDao.selectPage(param);
    }

    @Override
    public R getData(RedpackLogEntity log) {
        ProductCodeEntity entity = productCodeService.selectOne(new QueryWrapper<ProductCodeEntity>()
                .eq("product_id", log.getProductId()).eq("product_code", log.getProductCode()));
        int isTrue = 0;
        if (null != entity) {
            isTrue = 1;
        }
        Integer checkCount = this.checkLogService.getCheckCount(log.getProductCode());
        Date firstCheckTime = this.checkLogService.getFirstCheckTime(log.getProductCode());
        if (firstCheckTime == null) {
            throw new BaseException(ErrorInfo.OPERATION_ERROR);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");

        int canSend = 0;
        SendLogEntity one = sendLogService.selectOne(new QueryWrapper<SendLogEntity>()
                .eq("product_code", log.getProductCode()));
        if (null == one) {
            RedpackLogEntity selectOne = redpackLogDao.selectOne(new QueryWrapper<RedpackLogEntity>()
                    .eq("product_code", log.getProductCode()));
            if (null == selectOne) {
                canSend = 1;
            }
        }
        return R.ok().put("checkCount", checkCount)
                .put("firstCheckTime", sdf.format(firstCheckTime))
                .put("canSend", canSend)
                .put("productId", log.getProductId())
                .put("isTrue", isTrue);
    }

    @Override
    public Map<String, Object> getTodyData() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date beginTime = c.getTime();
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date endTime = c.getTime();
        //获取有效查询次数
        Integer activeCheckCount = this.checkLogService.getActiveCheckCount(beginTime, endTime);
        //获取总查询次数
        Integer allCheckCount = this.checkLogService.getAllCheckCount(beginTime, endTime);
        //获取红包发送个数
        Integer sendRedpackCount = redpackLogDao.getSendRedPackCount(beginTime, endTime);
        //获取发送红包金额
        BigDecimal sendRedpackAmount = redpackLogDao.getSendRedpackAmount(beginTime, endTime);
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("activeCheckCount", activeCheckCount);
        rs.put("allCheckCount", allCheckCount);
        rs.put("sendRedpackCount", sendRedpackCount);
        rs.put("sendRedpackAmount", sendRedpackAmount.divide(new BigDecimal(100)));
        return rs;
    }

    @Override
    public Map<String, Object> getAllData() {
        //获取有效查询次数
        Integer activeCheckCount = this.checkLogService.getActiveCheckCount(null, null);
        //获取总查询次数
        Integer allCheckCount = this.checkLogService.getAllCheckCount(null, null);
        //获取红包发送个数
        Integer sendRedpackCount = redpackLogDao.getSendRedPackCount(null, null);
        //获取发送红包金额
        BigDecimal sendRedpackAmount = redpackLogDao.getSendRedpackAmount(null, null);
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("activeCheckCount", activeCheckCount);
        rs.put("allCheckCount", allCheckCount);
        rs.put("sendRedpackCount", sendRedpackCount);
        rs.put("sendRedpackAmount", sendRedpackAmount.divide(new BigDecimal(100)));
        return rs;
    }

    @Override
    public List<RedpackLogVO> selectVOList(Map<String, Object> param) {
        return redpackLogDao.selectVOList(param);
    }

    @Override
    public Map<String, Object> getStatement(String beginTime, String endTime) throws ParseException {
        Map<String, Object> rs = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer sendRedpackCount = redpackLogDao.getSendRedPackCount(StringUtils.isBlank(beginTime) ? null : sdf.parse(beginTime), StringUtils.isBlank(endTime) ? null : sdf.parse(endTime));
        ;
        BigDecimal sendRedpackAmount = redpackLogDao.getSendRedpackAmount(StringUtils.isBlank(beginTime) ? null : sdf.parse(beginTime), StringUtils.isBlank(endTime) ? null : sdf.parse(endTime));
        rs.put("sendRedpackCount", sendRedpackCount);
        rs.put("sendRedpackAmount", sendRedpackAmount.divide(new BigDecimal(100)));
        return rs;
    }

    @Override
    public Map<String, Object> getThisMonthData() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date beginDate = c.getTime();
        Date endDate = new Date();
        //获取有效查询次数
        Integer activeCheckCount = this.checkLogService.getActiveCheckCount(beginDate, endDate);
        //获取总查询次数
        Integer allCheckCount = this.checkLogService.getAllCheckCount(beginDate, endDate);
        //获取红包发送个数
        Integer sendRedpackCount = redpackLogDao.getSendRedPackCount(beginDate, endDate);
        //获取发送红包金额
        BigDecimal sendRedpackAmount = redpackLogDao.getSendRedpackAmount(beginDate, endDate);
        Map<String, Object> rs = new HashMap<String, Object>();
        rs.put("activeCheckCount", activeCheckCount);
        rs.put("allCheckCount", allCheckCount);
        rs.put("sendRedpackCount", sendRedpackCount);
        rs.put("sendRedpackAmount", sendRedpackAmount.divide(new BigDecimal(100)));
        return rs;
    }

    @Override
    public void test1() {
        WxPayMicropayRequest request = WxPayMicropayRequest.newBuilder()
                .body("image形象店-深圳腾大- QQ公仔")
                .authCode("120061098828009406")
                .outTradeNo("987654321")
                .spbillCreateIp("8.8.8.8")
                .totalFee(1)
                .outTradeNo("1217752501201407033233368018")
                .build();
        try {
            WxPayMicropayResult micropay = this.wxPayService.micropay(request);
        } catch (WxPayException e) {
            e.printStackTrace();
        }
    }

}
