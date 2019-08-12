package com.dd.server.modules.test2.service;

import com.dd.server.modules.test2.entity.StudentSchoolEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 学生自主注册学校信息记录表 服务类
 * </p>
 *
 * @author Ivan
 * @since 2019-08-12
 */
public interface StudentSchoolService extends IService<StudentSchoolEntity> {

	PageInfo<StudentSchoolEntity> listByPage(int page, int limit);

}
