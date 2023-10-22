package com.yunxiao.service.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 接口api表
 *
 * @TableName api
 */
@Table(name = "api")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Api implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 请求体
     */
    private String body;

    /**
     * 请求头
     */
    private String headers;

    /**
     * 响应类型（0：text）
     */
    private Integer responseType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @Serial
    private static final long serialVersionUID = 1L;

}