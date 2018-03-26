package com.rayvision.rpc.web.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeansConfig
{
  private static Logger logger = LoggerFactory.getLogger(ApplicationBeansConfig.class);
  @Autowired
  private DubboProperties dubboProperties;
  
  @Bean
  public ApplicationConfig applicationConfig()
  {
    logger.info("dubbo消费者应用名称: " + this.dubboProperties.getDubboApplicationName());
    ApplicationConfig applicationConfig = new ApplicationConfig();
    applicationConfig.setName(this.dubboProperties.getDubboApplicationName());
    return applicationConfig;
  }
  
  @Bean
  public RegistryConfig registryConfig()
  {
    RegistryConfig registry = new RegistryConfig();
    logger.info("rpc-web注册中心协议: " + this.dubboProperties.getDubboRegProtocol());
    registry.setProtocol(this.dubboProperties.getDubboRegProtocol());
    logger.info("rpc-web注册中心地址: " + this.dubboProperties.getDubboRegAddress());
    registry.setAddress(this.dubboProperties.getDubboRegAddress());
    return registry;
  }
}
