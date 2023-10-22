package com.yunxiao.service.data.repository;

import com.yunxiao.service.data.model.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 0:04
 */
public interface UserRepository extends R2dbcRepository<User, Integer> {
}
