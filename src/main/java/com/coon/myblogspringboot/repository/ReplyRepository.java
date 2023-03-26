package com.coon.myblogspringboot.repository;

import com.coon.myblogspringboot.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply,Integer> {
}
