package com.shane.controller;

import com.shane.common.Result;
import com.shane.common.ResultUtils;
import com.shane.dto.ArticlePublishRequest;
import com.shane.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Shane
 * @Date: 2025/10/07/12:13
 * @Description:
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    /**
     * 发布文章，责任链模式，更容易扩展
     *
     * @param req
     * @return
     */
    @PostMapping("/article/publish")
    public Result<String> publish(@RequestBody ArticlePublishRequest req) {
        this.articleService.publish(req);
        return ResultUtils.success("发布成功");
    }
}
