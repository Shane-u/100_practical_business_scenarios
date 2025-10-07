package com.shane.article;

import com.shane.dto.ArticlePublishRequest;

/**
 * @Author: Shane
 * @Date: 2025/10/07/11:57
 * @Description:
 */
public abstract class AbstractCheck implements IArticlePublishCheck {

    private IArticlePublishCheck next;

    @Override
    public void check(ArticlePublishRequest request) {
        // 1、校验
        this.checkIn(request);
        // 2、顺着链条如果有校验器需要执行
        if (this.next != null) {
            this.next.check(request); // 递归调用
        }
    }

    protected abstract void checkIn(ArticlePublishRequest request);

    @Override
    public IArticlePublishCheck setNext(IArticlePublishCheck next) {
        this.next = next;
        return this.next;
    }
}
