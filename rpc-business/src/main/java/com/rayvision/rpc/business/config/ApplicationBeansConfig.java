package com.rayvision.rpc.business.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeansConfig {
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
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(this.dubboProperties.getDubboApplicationName());
        return applicationConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setServer("netty4");
        return providerConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registry = new RegistryConfig();
        registry.setProtocol(this.dubboProperties.getDubboRegProtocol());
        registry.setAddress(this.dubboProperties.getDubboRegAddress());
        return registry;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName(this.dubboProperties.getDubboProtocolName());
        protocol.setPort(this.dubboProperties.getDubboProtocolPort());
        protocol.setThreads(this.dubboProperties.getDubboProtocolThreads());
        protocol.setSerialization("kryo");
        protocol.setOptimizer("com.rayvision.rpc.api.SerializationOptimizerImpl");
        return protocol;
    }
}
