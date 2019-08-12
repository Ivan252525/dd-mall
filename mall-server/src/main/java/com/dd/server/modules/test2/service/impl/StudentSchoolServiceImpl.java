package com.dd.server.modules.test2.service.impl;

import com.dd.server.modules.test2.entity.StudentSchoolEntity;
import com.dd.server.modules.test2.dao.StudentSchoolMapper;
import com.dd.server.modules.test2.service.StudentSchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生自主注册学校信息记录表 服务实现类
 * </p>
 *
 * @author Ivan
 * @since 2019-08-12
 */
@Service
public class StudentSchoolServiceImpl extends ServiceImpl<StudentSchoolMapper, StudentSchoolEntity> implements StudentSchoolService {

	@Override
	public PageInfo<StudentSchoolEntity> listByPage(int page, int limit) {
		return PageHelper.startPage(page, limit).doSelectPageInfo(
				() -> baseMapper.selectAll()
		);
	}
}
