package com.dd.server.modules.test.controller;

import com.dd.common.web.BaseResult;
import com.dd.common.web.ResultUtils;
import com.dd.server.modules.test.dao.TestDao;
import com.dd.server.modules.test.entity.TestEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Ivan
 * @date: 2019/8/10 14:03
 */
@RestController
@RequestMapping("test")
public class TestController {

	@Autowired
	TestDao testDao;

	@GetMapping("test")
	public BaseResult<PageInfo<TestEntity>> test() {

		PageInfo<TestEntity> pageInfo = PageHelper.startPage(1, 3).doSelectPageInfo(
				() -> testDao.selectList(null));

		return ResultUtils.success(pageInfo);
	}

}
