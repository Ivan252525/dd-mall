package com.dd.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Ivan
 * @date: 2019/8/10 13:31
 */
@SpringBootApplication
@EnableTransactionManagement  // 开启事务注解
@MapperScan("com.dd.server.modules.*.dao")   // 扫描mapper接口
public class MallServerApplication {

	public static ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		applicationContext = SpringApplication.run(MallServerApplication.class, args);
	}

}
