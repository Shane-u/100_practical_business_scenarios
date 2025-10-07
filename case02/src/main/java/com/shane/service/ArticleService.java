package com.shane.service;

import com.shane.article.IArticlePublishCheck;
import com.shane.dto.ArticlePublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Shane
 * @Date: 2025/10/07/12:10
 * @Description:
 */
@Service
public class ArticleService {

    @Autowired
    private IArticlePublishCheck articlePublishCheck;


    /**
     * 发布文章
     *
     * @param request
     */
    public void publish(ArticlePublishRequest request) {
        // 1、文章合法性校验
        this.articlePublishCheck.check(request);

        // todo 业务逻辑处理
    }

}
