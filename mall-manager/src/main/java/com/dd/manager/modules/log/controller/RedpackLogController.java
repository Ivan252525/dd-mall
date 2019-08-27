package com.dd.manager.modules.log.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.manager.modules.log.entity.RedpackLogEntity;
import com.dd.manager.modules.log.service.RedpackLogService;
import com.dd.manager.modules.product.entity.ProductCodeEntity;
import com.dd.manager.modules.product.service.ProductCodeService;
import com.dd.manager.web.exception.BaseException;
import com.dd.manager.web.exception.message.ErrorInfo;
import com.dd.manager.web.result.R;
import com.dd.manager.web.validator.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2019-04-03 23:10:27
 */
@RestController
@RequestMapping("/openapi/redpack")
public class RedpackLogController {
    @Autowired
    private RedpackLogService redpackLogService;
    @Autowired
    private ProductCodeService productCodeService;
    
    private static final Logger logger = LoggerFactory.getLogger(RedpackLogController.class);
    
    @PostMapping("/send")
    public R send(RedpackLogEntity log) {
    	try {
			Assert.fieldCheck(log);
			//验证产品码真伪
	    	ProductCodeEntity entity = productCodeService.selectOne(new QueryWrapper<ProductCodeEntity>()
					.eq("product_id", log.getProductId()).eq("product_code", log.getProductCode()));
	    	if(entity == null) {
				throw new BaseException(ErrorInfo.FAKE__PSEUDO_CODE);
	    	}
		} catch (Exception e) {
			return R.error(e.getMessage());
		}
    	
    	return redpackLogService.send(log);
    	
    }
    
    @PostMapping("/getData")
    public R getData(RedpackLogEntity log) {
    	logger.info("收到验证请求：productCode:{},openId:{}",log.getProductCode(),log.getOpenId());
    	try {
			Assert.fieldCheck(log);
		} catch (Exception e) {
			return R.error(e.getMessage());
		}
    	R r = redpackLogService.getData(log);
    	logger.info("验证结果：{}",r);
    	return r;
    	
    }
}
