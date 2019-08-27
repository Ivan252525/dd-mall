package com.dd.manager.modules.rate.controller;

import com.dd.common.web.BaseResult;
import com.dd.common.web.ResultUtils;
import com.dd.manager.modules.rate.entity.RateEntity;
import com.dd.manager.modules.rate.service.RateService;
import com.dd.manager.web.annotation.Login;
import com.dd.manager.web.exception.BaseException;
import com.dd.manager.web.exception.message.ErrorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rate")
public class RateController {
	@Autowired
	private RateService rateService;
	
	@GetMapping("/getRateList")
	@Login
	public BaseResult<List<RateEntity>> getRateList() {
		List<RateEntity> data = rateService.getAllRate();
		return ResultUtils.success(data);
	}
	
	@PostMapping("/updateRate")
	@Login
	public BaseResult updateRate(@RequestBody List<RateEntity> list) {
		int allRate = 0;
		if(list == null) {
			throw new BaseException(ErrorInfo.MISS_PARAM);
		}
		for (RateEntity rateEntity : list) {
			int rate = rateEntity.getRate();
			if(rate < 0 || rate > 100) {
				throw new BaseException(ErrorInfo.PROPORTION_ERROR);
			}
			allRate += rateEntity.getRate();
		}
		if(allRate != 100) {
			throw new BaseException(ErrorInfo.PROPORTION_NOT_100);
		}
		this.rateService.updateBatchById(list);
		return ResultUtils.success();
	}
}
