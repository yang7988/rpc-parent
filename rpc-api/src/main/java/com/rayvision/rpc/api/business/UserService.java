package com.rayvision.rpc.api.business;

import com.rayvision.rpc.common.ApiResponse;

public interface UserService {
    ApiResponse getUserByUserName(String userName) throws Exception;
}
