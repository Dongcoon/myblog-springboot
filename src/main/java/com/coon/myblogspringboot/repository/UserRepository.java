package com.coon.myblogspringboot.repository;

import com.coon.myblogspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

//DAO
//자동으로 bean등록이 된다.
//@Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
    //**JPA Naming전략**
    //Select * from user where username = ? and password = ?;
    User findByUsernameAndPassword(String username, String password);

//    @Query(value = "Select * from user where username = ? and password = ?", nativeQuery = true)
//    User login(String username, String password);

}
