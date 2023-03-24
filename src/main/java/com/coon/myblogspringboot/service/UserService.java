package com.coon.myblogspringboot.service;

import com.coon.myblogspringboot.model.RoleType;
import com.coon.myblogspringboot.model.User;
import com.coon.myblogspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public void 회원가입(User user){
        String rawPassword = user.getPassword(); //원문
        String encPassword = encoder.encode(rawPassword);
        user.setRole(RoleType.USER);
        user.setPassword(encPassword);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User 회원찾기(String username){
        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;
    }

    @Transactional
    public void 회원수정(User user){
        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        //select를 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서!!
        //영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌.
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
           return new IllegalArgumentException("회원 찾기 실패");
        });

        // Validate 체크
        if(persistance.getOauth() == null || persistance.getOauth().equals("")){
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        }

        // 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 실행
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹함

        // 여기서는 트랜잭션이 종료되기 때문에 DB 값은 변경이 됐음.
        // 하지만 세션값은 변경되지 않은 상태이기 때문에 내가 "직접 세션값을 변경"해줄 것임.
        // 세션값을 변경하는 방법은 스프링 시큐리티가 어떻게 로그인이 되는지,
        // 로그인이 될 때 세션이 어떻게 만들어지는지에 대한 기본 개념이 필요함
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }
}

