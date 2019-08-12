package com.dd.server.modules.test2.controller;


import com.dd.common.web.BaseResult;
import com.dd.common.web.ResultUtils;
import com.dd.common.web.page.PageForm;
import com.dd.server.modules.test2.entity.StudentSchoolEntity;
import com.dd.server.modules.test2.service.StudentSchoolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 学生自主注册学校信息记录表 前端控制器
 * </p>
 *
 * @author Ivan
 * @since 2019-08-12
 */
@RestController
@RequestMapping("/test2/student-school-entity")
public class StudentSchoolController {

	@Autowired
	StudentSchoolService studentSchoolService;

	@PostMapping("test2")
	public BaseResult<PageInfo<StudentSchoolEntity>> test2(@Valid @RequestBody PageForm form) {
		return ResultUtils.success(studentSchoolService.listByPage(form.getPage(), form.getLimit()));
	}

}

