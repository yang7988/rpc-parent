package com.rayvision.rpc.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.function.Consumer;

@Configuration
public class ApplicationBeansConfig {
    private static Logger logger = LoggerFactory.getLogger(ApplicationBeansConfig.class);
    @Autowired
    private DubboProperties dubboProperties;

    @Bean(name = {"dataSource"}, destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        logger.info("dubbo消费者应用名称: " + this.dubboProperties.getDubboApplicationName());
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(this.dubboProperties.getDubboApplicationName());
        applicationConfig.setRegistries(Arrays.asList(registry()/*,nacosRegistry()*/));
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registry() {
        RegistryConfig registry = new RegistryConfig();
        logger.info("rpc-web注册中心协议: " + this.dubboProperties.getDubboRegProtocol());
        registry.setProtocol(this.dubboProperties.getDubboRegProtocol());
        logger.info("rpc-web注册中心地址: " + this.dubboProperties.getDubboRegAddress());
        registry.setAddress(this.dubboProperties.getDubboRegAddress());
        return registry;
    }
    /*@Bean
    public RegistryConfig nacosRegistry() {
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress(this.dubboProperties.getNacosRegAddress());
        return registry;
    }*/
}
