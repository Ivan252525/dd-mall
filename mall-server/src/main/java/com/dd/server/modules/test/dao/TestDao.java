package com.dd.server.modules.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dd.server.modules.test.entity.TestEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ivan
 * @date: 2019/8/10 14:24
 */
@Repository
public interface TestDao extends BaseMapper<TestEntity> {

	List<TestEntity> selectAll();

}
