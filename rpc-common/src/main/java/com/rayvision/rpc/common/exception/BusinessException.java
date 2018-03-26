package com.rayvision.rpc.common.exception;

import com.rayvision.rpc.common.enums.ApiResponseEnum;

public class BusinessException extends RuntimeException
{
  ApiResponseEnum apiResponseEnum;
  Object errorData;
  
  public BusinessException(RuntimeException e)
  {
    super(e);
  }
  
  public BusinessException(ApiResponseEnum apiResponseEnum, Object errorData)
  {
    this.apiResponseEnum = apiResponseEnum;
    this.errorData = errorData;
  }
  
  public BusinessException(ApiResponseEnum apiResponseEnum)
  {
    super(apiResponseEnum.getLabel());
    this.apiResponseEnum = apiResponseEnum;
  }
  
  public BusinessException(String errMsg)
  {
    super(errMsg);
  }
  
  public ApiResponseEnum getApiResponseEnum()
  {
    return this.apiResponseEnum;
  }
  
  public Object getErrorData()
  {
    return this.errorData;
  }
}
