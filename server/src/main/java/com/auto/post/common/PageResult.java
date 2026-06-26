package com.auto.post.common;

import java.util.List;

/**
 * 分页响应结构，用于列表类接口返回分页数据。
 *
 * @param records 当前页记录
 * @param total 总记录数
 * @param pageIndex 当前页码，从 1 开始
 * @param pageSize 每页数量
 * @param <T> 记录类型
 */
public record PageResult<T>(List<T> records, long total, int pageIndex, int pageSize) {

    public static <T> PageResult<T> of(List<T> all, int pageIndex, int pageSize) {
        int safeIndex = Math.max(pageIndex, 1);
        int safeSize = Math.max(pageSize, 1);
        int from = Math.min((safeIndex - 1) * safeSize, all.size());
        int to = Math.min(from + safeSize, all.size());
        return new PageResult<>(all.subList(from, to), all.size(), safeIndex, safeSize);
    }
}
