package com.lpgxp.chapter.web;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 努力常态化
 * date 2018/4/12  23:05
 */
@RestController
public class HelloController {

    @RequestMapping("/index")
    public String index() {
        return "hello";
    }

}
