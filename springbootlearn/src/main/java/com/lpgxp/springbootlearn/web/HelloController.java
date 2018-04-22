package com.lpgxp.springbootlearn.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 努力常态化  2018/4/18 0:17
 */
@RestController
public class HelloController {

    @PostMapping("/hello")
    public String index() {
        return "hello";
    }

}
