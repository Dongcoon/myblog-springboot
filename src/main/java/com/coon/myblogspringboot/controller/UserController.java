package com.coon.myblogspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static이하에 있는 /js/**, /css/**, /image/** 허용
@Controller
public class UserController {
    @GetMapping("/joinForm")
    public String joinForm(){
        return "/auth/user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "/auth/user/loginForm";
    }

}
