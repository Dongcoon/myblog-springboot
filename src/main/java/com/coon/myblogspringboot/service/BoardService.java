package com.coon.myblogspringboot.service;

import com.coon.myblogspringboot.model.Board;
import com.coon.myblogspringboot.model.RoleType;
import com.coon.myblogspringboot.model.User;
import com.coon.myblogspringboot.repository.BoardRepository;
import com.coon.myblogspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Transactional
    public void 글쓰기(Board board, User user){ // title, content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
}
