package com.coon.myblogspringboot.controller.api;

import com.coon.myblogspringboot.config.auth.PrincipalDetail;
import com.coon.myblogspringboot.dto.ResponseDto;
import com.coon.myblogspringboot.model.Board;
import com.coon.myblogspringboot.model.User;
import com.coon.myblogspringboot.service.BoardService;
import com.coon.myblogspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        System.out.println("글쓰기 호출됨");
        boardService.글쓰기(board,principal.getUser());

        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

}
