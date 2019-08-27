package com.dd.manager.modules.log.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.manager.modules.log.dao.RedpackLogDao;
import com.dd.manager.modules.log.dao.SendLogDao;
import com.dd.manager.modules.log.entity.RedpackLogEntity;
import com.dd.manager.modules.log.entity.SendLogEntity;
import com.dd.manager.modules.log.service.CheckLogService;
import com.dd.manager.modules.log.service.RedpackLogService;
import com.dd.manager.modules.log.service.SendLogService;
import com.dd.manager.modules.log.vo.SendLogVO;
import com.dd.manager.modules.product.entity.ProductCodeEntity;
import com.dd.manager.modules.product.service.ProductCodeService;
import com.dd.manager.web.exception.BaseException;
import com.dd.manager.web.exception.message.ErrorInfo;
import com.dd.manager.web.result.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("sendLogService")
public class SendLogServiceImpl implements SendLogService {

	@Autowired
	SendLogDao sendLogDao;
	
	@Autowired
	private CheckLogService checkLogService;
	@Autowired
	private ProductCodeService productCodeService;
	@Autowired
	private RedpackLogDao redpackLogDao;

	@Override
	public R getData(RedpackLogEntity log) {
		ProductCodeEntity entity = productCodeService.selectOne(new QueryWrapper<ProductCodeEntity>()
				.eq("product_id", log.getProductId()).eq("product_code", log.getProductCode()));
		int isTrue = 0;
		if(null != entity) {
			isTrue = 1;
		}
		Integer checkCount = this.checkLogService.getCheckCount(log.getProductCode());
		Date firstCheckTime = this.checkLogService.getFirstCheckTime(log.getProductCode());
		if(firstCheckTime == null) {
			throw new BaseException(ErrorInfo.OPERATION_ERROR);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
		
		int canSend = 0;
		RedpackLogEntity selectOne = redpackLogDao.selectOne(new QueryWrapper<RedpackLogEntity>()
				.eq("product_code", log.getProductCode()));
		if(null == selectOne) {
			SendLogEntity one = sendLogDao.selectOne(new QueryWrapper<SendLogEntity>()
					.eq("product_code", log.getProductCode()));
			if(null == one) {
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
	public synchronized R send(SendLogEntity log) {
		SendLogEntity one = sendLogDao.selectOne(new QueryWrapper<SendLogEntity>()
				.eq("product_code", log.getProductCode()));
		if(one != null) {
			return R.error("该奖品已被领取");
		}
		log.setCreateTime(new Date());
		log.setStatus(1);
		sendLogDao.insert(log);
		return R.ok("奖品领取成功");
	}
	@Override
	public List<SendLogVO> selectVOList(Map<String, Object> param) {
		return sendLogDao.selectVOList(param);
	}
	@Override
	public void batchSend(Long[] ids) {
		sendLogDao.batchSend(ids);
	}

	@Override
	public SendLogEntity selectOne(QueryWrapper<SendLogEntity> query) {
		return sendLogDao.selectOne(query);
	}

}
