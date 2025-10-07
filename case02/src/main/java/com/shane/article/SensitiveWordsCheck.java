package com.shane.article;


import com.shane.common.BusinessExceptionUtils;
import com.shane.dto.ArticlePublishRequest;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Shane
 * @Date: 2025/10/07/12:01
 * @Description: 敏感词校验
 */
public class SensitiveWordsCheck extends AbstractCheck {

    @Override
    protected void checkIn(ArticlePublishRequest req) {
        //  敏感词列表
        List<String> sensitiveWordsList = Arrays.asList("暴力","Shane");
        //  有敏感词则抛出异常
        for (String sw : sensitiveWordsList) {
            if (req.getContent().contains(sw)) {
                throw BusinessExceptionUtils.businessException("发现敏感词：" + sw);
            }
        }
    }
}