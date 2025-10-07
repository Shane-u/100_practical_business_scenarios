package com.shane.article;

import com.shane.common.BusinessExceptionUtils;
import com.shane.dto.ArticlePublishRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Shane
 * @Date: 2025/10/07/12:01
 * @Description: 文章长度校验
 */
public class ContentLengthCheck extends AbstractCheck {

    @Override
    protected void checkIn(ArticlePublishRequest request) {
        if (StringUtils.length(request.getContent()) > 10000) {
            throw BusinessExceptionUtils.businessException("文章长度不能超过10000个字符");
        }
    }
}
