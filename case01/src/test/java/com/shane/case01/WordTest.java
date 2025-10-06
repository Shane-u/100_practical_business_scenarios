package com.shane.case01;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.BorderStyle;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.DocumentRenderPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Shane
 * @Date: 2025/10/06/20:12
 * @Description:
 */
public class WordTest {

    @Test
    public void WordCompileTest() {
        // 1、生成一个Word模板
        DocumentRenderData document = Documents.of()
                .addParagraph(Paragraphs.of("{{title}}").create()) // 生成一段文本的占位符
                .addParagraph(Paragraphs.of("{{#table}}").create())
                .create();

        XWPFTemplate template = XWPFTemplate.create(document);
        try {
            template.writeAndClose(Files.newOutputStream(Paths.get("src\\main\\resources\\template.docx")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 2、利用模板往里面填入信息
        // 准备信息
        Map<String, Object> map = new HashMap<>();
        String text = "Shane的基本信息";
        map.put("title", Texts.of(text).bold().color("3f4f3f").create());
        map.put("table", Tables.of(new String[][]{
                new String[]{"年龄", "性别"},
                new String[]{"20", "男"},
        }).border(BorderStyle.DEFAULT).create());

        // 2、开始利用模板进行渲染并写入文件
        String path = "src\\main\\resources\\template.docx";
        try {
            XWPFTemplate.compile(path).render(map).writeAndClose(Files.newOutputStream(Paths.get("src\\main\\resources\\last.docx")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
