package com.yunxiao.service.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName trigger
 */
@Table(name = "api_trigger")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiTrigger implements Serializable {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 接口id
     */
    @Column("api_id")
    private Integer apiId;

    /**
     * 用户id
     */
    @Column("user_id")
    private Long userId;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 上次执行时间
     */
    @Column("last_exec")
    private LocalDateTime lastExec;

    /**
     * 触发类型（-1：任何时候不触发，0：任何时候触发，1：根据Http状态码触发，2：动态根据Json指定值触发）
     */
    @Column("trigger_type")
    private Integer triggerType;

    /**
     * 预期结果
     */
    @Column("expect_data")
    private String expectData;

    /**
     * 状态（0：未启用，1：启用）
     */
    private Integer status;

    /**
     * 执行类型，逗号分割（0：发送邮件）
     */
    private String execType;

    /**
     * 通知邮箱
     */
    @Column("expected_mail")
    private String expectedMail;

    /**
     * 通知主题
     */
    @Column("expected_subject")
    private String expectedSubject;

    /**
     * 通知信息
     */
    @Column("expected_text")
    private String expectedText;

    /**
     * 存放变量
     */
    @Column("expected_var")
    private String expectedVar;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    @Serial
    private static final long serialVersionUID = 1L;
}