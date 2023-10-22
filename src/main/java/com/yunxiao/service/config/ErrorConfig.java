package com.yunxiao.service.config;

import com.yunxiao.service.data.model.support.ErrorCode;
import com.yunxiao.spring.reactive.model.result.Result;
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
public class ErrorConfig {

    @ExceptionHandler(DecodingException.class)
    public Mono<Result<Void>> decodingException(DecodingException e) {

        return Result.ofNull().codeEnum(ErrorCode.REQUEST_PARAM_ERROR).toMono();
    }
}
