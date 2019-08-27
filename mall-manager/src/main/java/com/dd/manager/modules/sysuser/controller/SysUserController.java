package com.dd.manager.modules.sysuser.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dd.common.utils.md5.MD5;
import com.dd.manager.modules.sysuser.entity.SysUserEntity;
import com.dd.manager.modules.sysuser.form.LoginForm;
import com.dd.manager.modules.sysuser.service.SysUserService;
import com.dd.manager.web.result.R;
import com.dd.manager.web.security.interceptor.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 *
 * @author lihj
 * @email 
 * @date 2019-04-01 21:22:48
 */
@RestController
@RequestMapping("/openapi/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    
    @Autowired
	private JwtUtils jwtUtils;
   @PostMapping("/login")
   public R login(LoginForm form) {
	   
	   SysUserEntity user = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("user_name", form.getUserName())
			   													  .eq("password", MD5.md5(form.getPassword())));
	   if(user == null) {
		   return R.error("登录失败！");
	   }
	   String token = jwtUtils.generateToken(user.getId());
	   return R.ok().put("token", token);
   }
}
