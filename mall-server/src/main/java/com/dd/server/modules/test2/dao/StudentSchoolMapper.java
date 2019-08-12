package com.dd.server.modules.test2.dao;

import com.dd.server.modules.test2.entity.StudentSchoolEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 学生自主注册学校信息记录表 Mapper 接口
 * </p>
 *
 * @author Ivan
 * @since 2019-08-12
 */
public interface StudentSchoolMapper extends BaseMapper<StudentSchoolEntity> {

	List<StudentSchoolEntity> selectAll();

}
