package com.coon.myblogspringboot.repository;

import com.coon.myblogspringboot.model.Board;
import com.coon.myblogspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//DAO
//자동으로 bean등록이 된다.
//@Repository 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {

}