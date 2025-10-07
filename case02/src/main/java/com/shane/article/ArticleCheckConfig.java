package com.shane.article;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Shane
 * @Date: 2025/10/07/12:07
 * @Description:
 */
@Configuration
public class ArticleCheckConfig {

    @Bean
    public ContentLengthCheck contentLengthCheck() {
        return new ContentLengthCheck();
    }

    @Bean
    public SensitiveWordsCheck sensitiveWordsCheck() {
        return new SensitiveWordsCheck();
    }

    /*返回接口的原因是调用方只依赖接口不关心具体实现*/
    @Bean
    public IArticlePublishCheck articlePublishCheck() { // 一般Bean的名字就是函数名
        ContentLengthCheck firstCheck = this.contentLengthCheck();
        firstCheck.setNext(this.sensitiveWordsCheck());
        return firstCheck;
    }

}
