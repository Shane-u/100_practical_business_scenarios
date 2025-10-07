package com.shane.article;

import com.shane.dto.ArticlePublishRequest;

/**
 * @Author: Shane
 * @Date: 2025/10/07/11:53
 * @Description:
 */
public interface IArticlePublishCheck {
    /**
     * 对文章进行校验
     *
     * @param request
     */
    void check(ArticlePublishRequest request);


    /**
     * 链条的两端，设置下一个文章校验器子阶段
     *
     * @param next
     * @return
     */
    IArticlePublishCheck setNext(IArticlePublishCheck next);
}
