package com.yunxiao.service.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 *
 * @TableName user
 */
@Table(name = "user")
@Data
public class User implements Serializable {
    /**
     * 用户id-雪花id
     */
    @Id
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}