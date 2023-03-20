package com.coon.myblogspringboot.service;

import com.coon.myblogspringboot.model.RoleType;
import com.coon.myblogspringboot.model.User;
import com.coon.myblogspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Transactional
    public void 회원가입(User user){
        String rawPassword = user.getPassword(); //원문
        String encPassword = encoder.encode(rawPassword);
        user.setRole(RoleType.USER);
        user.setPassword(encPassword);
        userRepository.save(user);
    }
}
