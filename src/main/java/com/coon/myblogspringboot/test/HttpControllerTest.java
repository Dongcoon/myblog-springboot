package com.coon.myblogspringboot.test;

import org.springframework.web.bind.annotation.*;

@RestController
public class HttpControllerTest {

    //인터넷 브라우저에서는 get요청만 가능
    @GetMapping("/http/get")
    public String getTest(Member member) {

        return "get 요청:" + member.getId()+", "+member.getUsername()+", "+member.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member member) { // MessageConverter(스프링부터)
        return "post 요청:" + member.getId()+", "+member.getUsername()+", "+member.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member member) {

        return "put 요청:" + member.getId()+", "+member.getPassword();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
