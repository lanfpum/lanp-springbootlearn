package com.lpgxp.springbootlearn.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 努力常态化  2018/4/22 21:36
 */
@RestController
public class CookieController {

    @GetMapping("/test/cookie")
    public void cookie(@RequestParam("browser") String browser, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object res = session.getAttribute("browser");

        if (res == null) {
            System.out.println("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            System.out.println("不存在session，设置browser=" + session.getAttribute("browser"));
        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null&& cookies.length > 0 ) {
            for (Cookie c : cookies) {
                System.out.println(c.getName() + " : " + c.getValue());
            }
        }
    }


}
