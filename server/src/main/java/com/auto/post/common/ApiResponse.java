package com.auto.post.common;

/**
 * 后端统一响应结构，用于所有 Controller 返回稳定的 code/message/data。
 *
 * @param code 业务响应码，0 表示成功
 * @param message 面向前端展示的响应说明
 * @param data 响应数据主体
 * @param <T> 响应数据类型
 */
public record ApiResponse<T>(int code, String message, T data) {

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(0, "success", data);
    }

    public static ApiResponse<Void> ok() {
        return new ApiResponse<>(0, "success", null);
    }

    public static ApiResponse<Void> fail(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }
}
