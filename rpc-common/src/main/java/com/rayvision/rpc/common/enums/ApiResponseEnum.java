package com.rayvision.rpc.common.enums;

import java.io.Serializable;

public enum ApiResponseEnum implements Serializable {
    SUCCESS(200, "SUCCESS", "成功", true),
    FAIL(100, "FAIL", "失败", false),
    FORBIDDEN(403, "FORBIDDEN", "没有权限", false),
    RESOURCE_NOT_FOUND(404, "RESOURCE_NOT_FOUND", "资源不存在", false),
    INTERNAL_ERROR(500, "INTERNAL_ERROR", "服务器处理失败", false),
    PARAMETER_CANT_BE_EMPTY(601, "PARAMETER_CANT_BE_EMPTY", "缺少必要参数", false),
    DO_NOT_HAVE_ANY_MORE_RECORD(700, "DO_NOT_HAVE_ANY_MORE_RECORD", "没有更多记录", false);

    protected int id;
    protected String code;
    protected String label;
    protected boolean success;

    private ApiResponseEnum(int id, String code, String label, boolean success) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.success = success;
    }

    public int getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getLabel() {
        return this.label;
    }

    public boolean success() {
        return this.success;
    }
}
