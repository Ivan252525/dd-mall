package com.dd.manager.modules.test.controller;


import com.dd.common.web.BaseResult;
import com.dd.common.web.ResultUtils;
import com.dd.manager.modules.test.entity.StudentSchoolEntity;
import com.dd.manager.modules.test.service.StudentSchoolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 学生自主注册学校信息记录表 前端控制器
 * </p>
 *
 * @author Ivan
 * @since 2019-08-12
 */
@RestController
@RequestMapping("/test/student-school-entity")
public class StudentSchoolController {

    @Autowired
    StudentSchoolService studentSchoolService;

    @GetMapping("test")
    public BaseResult<PageInfo<StudentSchoolEntity>> test() {
        PageInfo<StudentSchoolEntity> pageInfo = PageHelper.startPage(1, 3).doSelectPageInfo(
                () -> studentSchoolService.list()
        );
        return ResultUtils.success(pageInfo);
    }

}

