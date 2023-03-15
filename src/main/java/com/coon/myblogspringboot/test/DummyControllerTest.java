package com.coon.myblogspringboot.test;

import com.coon.myblogspringboot.model.RoleType;
import com.coon.myblogspringboot.model.User;
import com.coon.myblogspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {
    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    //save함수는 id를 전달하지 않으면 insert 실행,
    //id 전달시 id에 대한 데이터가 있으면 update 실행,
    //id 전달시 id에 대한 데이터가 없으면 insert 실행.

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){ // json -> Java Object로 변환
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalStateException("업데이트에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user);

        //더티 체킹
        return null;
    }


    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }
    //한페이지당 2건에 데이터를 리턴받음
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}") //{id}주소로 파라미터를 전달 받을 수 있음.
    public User detail(@PathVariable int id){
        // user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 됨
        // 그럼 return시 null이 리턴이 됨 >> 에러발생
        // Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해라

//        람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalStateException("존재하지 않은 유저입니다. id:" + id);
//        });
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalStateException>() {
            @Override
            public IllegalStateException get() {
                return new IllegalStateException("존재하지 않은 유저입니다. id:" + id);
            }
        });
        //user 객체 = 자바 오브젝트
        // 변환(웹브라우저가 이해할 수 있는 데이터) -> json
        // Springboot = MessageConverter라는 애가 응답시에 자동 작동
        // 자바 오브젝트 리턴시 MessageConverter가 Jackson 자이브러리 호출해서
        // user 오브젝트를 json 으로 변환해서 브라우저에게 던져줌.
        return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id: "+user.getId());
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("email: "+user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";

    }


}
