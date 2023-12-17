package com.yunxiao.service.config;

import com.yunxiao.service.data.support.ErrorCode;
import com.yunxiao.spring.core.protocol.BusinessException;
import com.yunxiao.spring.core.protocol.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.DecodingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

/**
 * @author LuoYunXiao
 * @since 2023/10/22 15:36
 */
@Configuration
@RestControllerAdvice
@Slf4j
public class ErrorConfig {

    @ExceptionHandler(DecodingException.class)
    public Mono<Result<Void>> decodingException(DecodingException e) {
        if (log.isDebugEnabled()) {
            log.error("请求解码失败", e);
        }
        return Mono.just(Result.ofNull().codeAble(ErrorCode.REQUEST_PARAM_ERROR).build());
    }

    @ExceptionHandler(BusinessException.class)
    public Mono<Result<Void>> businessException(BusinessException e) {
        if (log.isDebugEnabled()) {
            log.error("业务异常", e);
        }
        return Mono.just(Result.ofNull().code(e.getCode()).msg(e.getMsg()).tips(e.getTips()).build());
    }
}
