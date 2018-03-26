package com.rayvision.rpc.web.handler;

import com.rayvision.rpc.common.ApiResponse;
import com.rayvision.rpc.common.exception.BusinessException;
import com.rayvision.rpc.common.enums.ApiResponseEnum;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常拦截器
 */

@ControllerAdvice
public class RestExceptionInterceptor {
    private static Logger logger = LoggerFactory.getLogger(RestExceptionInterceptor.class);

    @ExceptionHandler({BusinessException.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> internalExceptionHandler(RuntimeException e)
    {
        logger.error("internalExceptionHandler: stacktrace={}", ExceptionUtils.getStackTrace(e));
        if ((e instanceof BusinessException))
        {
            BusinessException exception = (BusinessException) e;
            if (exception.getApiResponseEnum() == null)
            {
                ApiResponse response = ApiResponse.returnFail(exception.getErrorData(), exception.getMessage());
                return new ResponseEntity(response, HttpStatus.OK);
            }
            ApiResponse apiResponse = ApiResponse.returnFail(exception.getErrorData(), exception.getApiResponseEnum());
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }
        int code = HttpStatus.BAD_REQUEST.value();
        String message = e.getMessage();
        return new ResponseEntity(ApiResponse.returnFail(code, message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<?> exceptionHandler(Exception e) {
        logger.error("exceptionHandler: stacktrace={}", ExceptionUtils.getStackTrace(e));
        ApiResponse apiResponse = ApiResponse.returnFail(ApiResponseEnum.INTERNAL_ERROR);
        return new ResponseEntity(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
