package com.rayvision.rpc.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboProperties
{
  @Value("${dubbo.provider.application.name}")
  private String dubboApplicationName;
  @Value("${dubbo.provider.register.protocol}")
  private String dubboRegProtocol;
  @Value("${dubbo.provider.register.address}")
  private String dubboRegAddress;
  @Value("${dubbo.protocol.name}")
  private String dubboProtocolName;
  @Value("${dubbo.protocol.port}")
  private Integer dubboProtocolPort;
  @Value("${dubbo.protocol.threads}")
  private Integer dubboProtocolThreads;
  
  public String getDubboApplicationName()
  {
    return this.dubboApplicationName;
  }
  
  public String getDubboRegProtocol()
  {
    return this.dubboRegProtocol;
  }
  
  public String getDubboRegAddress()
  {
    return this.dubboRegAddress;
  }
  
  public String getDubboProtocolName()
  {
    return this.dubboProtocolName;
  }
  
  public Integer getDubboProtocolPort()
  {
    return this.dubboProtocolPort;
  }
  
  public Integer getDubboProtocolThreads()
  {
    return this.dubboProtocolThreads;
  }
}
