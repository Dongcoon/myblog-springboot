package com.coon.myblogspringboot.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    // http://localhost:8001/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("temphome()");
//        파일리턴 기본경로: src/main/resources/static -> 정적파일만
//        리턴명: /home.html
//        풀경로: src/main/resources/static/home.html

        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg(){
        return"/test.png";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp(){
        //prefix: /WEB-INF/views/
        //suffix: .jsp
        //풀네임: /WEB-INF/views/index.jsp

        return"index";
    }
}
