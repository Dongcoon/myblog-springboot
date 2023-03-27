package com.coon.myblogspringboot.repository;

import com.coon.myblogspringboot.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {
    //nativeQuery 사용시 springboot verion 변경 2.7.9 -> 2.7.0
    @Modifying
    @Query(value = "INSERT INTO Reply(userId, boardId, content, createDate) values(?1,?2,?3,now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content);
}
