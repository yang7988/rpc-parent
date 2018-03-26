package com.rayvision.rpc.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProperties {
    @Value("${dubbo.consumer.application.name}")
    private String dubboApplicationName;
    @Value("${dubbo.register.protocol}")
    private String dubboRegProtocol;
    @Value("${dubbo.register.address}")
    private String dubboRegAddress;

    public String getDubboApplicationName() {
        return this.dubboApplicationName;
    }

    public String getDubboRegProtocol() {
        return this.dubboRegProtocol;
    }

    public String getDubboRegAddress() {
        return this.dubboRegAddress;
    }
}
