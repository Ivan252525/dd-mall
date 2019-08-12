package com.dd.server.modules.test.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author: Ivan
 * @date: 2019/8/10 14:25
 */
@TableName("tb_teacher_np_account")
public class TestEntity {

	@TableId
	private Long id;

	private Long teacherId;

	private Long npAccount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public Long getNpAccount() {
		return npAccount;
	}

	public void setNpAccount(Long npAccount) {
		this.npAccount = npAccount;
	}
}
