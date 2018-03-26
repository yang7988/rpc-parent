package com.rayvision.rpc.web;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.rayvision.rpc.web"})
@EnableDubbo(scanBasePackages={"com.rayvision.rpc.web"})
public class RpcWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcWebApplication.class, args);
	}
}
