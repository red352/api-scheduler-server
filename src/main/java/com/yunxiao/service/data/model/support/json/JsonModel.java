package com.yunxiao.service.data.model.support.json;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author LuoYunXiao
 * @since 2023/10/21 15:41
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JsonModel {

    Map<List<String>, String> expectData;

    Map<String, List<String>> headers;

    Map<String, List<String>> params;
}
