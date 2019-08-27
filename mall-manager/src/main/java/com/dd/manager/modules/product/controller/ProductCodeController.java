package com.dd.manager.modules.product.controller;

import com.dd.common.utils.md5.MD5;
import com.dd.manager.modules.product.entity.ProductCodeEntity;
import com.dd.manager.modules.product.service.ProductCodeService;
import com.dd.manager.modules.rate.entity.RateEntity;
import com.dd.manager.modules.rate.service.RateService;
import com.dd.manager.web.config.profile.RedPackProfile;
import com.dd.manager.web.config.sys.SysProperties;
import com.dd.manager.web.exception.BaseException;
import com.dd.manager.web.exception.message.ErrorInfo;
import com.dd.manager.web.security.interceptor.JwtUtils;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author lihj
 * @email
 * @date 2019-04-26 23:48:50
 */
@Controller
@RequestMapping("/api/productcode")
public class ProductCodeController {
    @Autowired
    private ProductCodeService productCodeService;
    @Autowired
    private SysProperties sysProperties;
    @Autowired
    private RedPackProfile redPackProfile;
    @Autowired
    private RateService rateService;
    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping("/genCode")
//  @Login
    @ResponseBody
    public void genCode(@RequestParam("count") Integer count, @RequestParam("token") String token, HttpServletResponse response) {
        if (count == null) {
            return;
        }
        if (count.intValue() <= 0 || count.intValue() % 10 != 0) {
            throw new BaseException(ErrorInfo.QR_COUNT_ERROR);
        }
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            throw new BaseException(ErrorInfo.NOT_LOGIN);
        }
        int countNum = count.intValue();
        String content = redPackProfile.getForeDomain() + "/kongbai.html?pid=%s&pcode=%s&type=%s";
        response.setContentType("application/octet-stream");
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        List<ProductCodeEntity> productCodeList = new ArrayList<ProductCodeEntity>();
        List<String> codeList = new ArrayList<String>();
        Date createTime = new Date();
        //获取比例
        List<RateEntity> rates = rateService.selectByMap(new HashMap<String, Object>());
        try {
            response.setHeader("Content-disposition",
                    "attachment; filename=" + new String(("二维码链接列表".getBytes()),
                            "ISO-8859-1") + ".txt");
            OutputStream outputStream = response.getOutputStream();
            PrintWriter pw = new PrintWriter(outputStream);
            int type1Count = 0;
            int type2Count = 0;
            for (RateEntity rateEntity : rates) {
                if (rateEntity.getType().intValue() == 1) {
                    type1Count = countNum * rateEntity.getRate() / 100;
                } else if (rateEntity.getType().intValue() == 2) {
                    type2Count = countNum * rateEntity.getRate() / 100;
                }
            }
            if (type1Count > 0) {
                String productId = "001";
                for (int i = 1; i <= type1Count; i++) {
                    String productCode = UUID.randomUUID().toString().replaceAll("-", "");
                    ProductCodeEntity e = new ProductCodeEntity();
                    e.setCreateTime(createTime);
                    e.setProductCode(productCode);
                    e.setProductId(productId);
                    productCodeList.add(e);
                    String type = MD5.md5(productCode + "1");
                    String url = String.format(content, productId, productCode, type);
                    codeList.add(url);
//      			pw.println(url);
                }
            }
            if (type2Count > 0) {
                String productId = "002";
                for (int j = 1; j <= type2Count; j++) {
                    String productCode = UUID.randomUUID().toString().replaceAll("-", "");
                    ProductCodeEntity e = new ProductCodeEntity();
                    e.setCreateTime(createTime);
                    e.setProductCode(productCode);
                    e.setProductId(productId);
                    productCodeList.add(e);
                    String type = MD5.md5(productCode + "2");
                    String url = String.format(content, productId, productCode, type);
                    codeList.add(url);
//      			pw.println(url);
                }
            }
            this.productCodeService.insertBatch(productCodeList, (countNum / 1000) + 1);
            Collections.shuffle(codeList);
            for (String url : codeList) {
                pw.println(url);
            }
            pw.flush();
            pw.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {

        }
    }
}
