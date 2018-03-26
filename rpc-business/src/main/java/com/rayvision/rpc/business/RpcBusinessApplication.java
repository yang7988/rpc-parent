package com.rayvision.rpc.business;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.rayvision.rpc.business")
@MapperScan(basePackages = "com.rayvision.rpc.business.mapper")
@EnableDubbo(scanBasePackages = "com.rayvision.rpc.business")
public class RpcBusinessApplication {

	private static Logger log = LoggerFactory.getLogger(RpcBusinessApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RpcBusinessApplication.class, args);
		log.info("rpc-business project 启动成功");
	}
}
