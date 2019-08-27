package com.dd.manager.modules.sysuser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dd.manager.modules.sysuser.dao.SysUserDao;
import com.dd.manager.modules.sysuser.entity.SysUserEntity;
import com.dd.manager.modules.sysuser.service.SysUserService;
import org.springframework.stereotype.Service;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	
	
}
