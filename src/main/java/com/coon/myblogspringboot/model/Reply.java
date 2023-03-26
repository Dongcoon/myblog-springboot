package com.coon.myblogspringboot.model;

import com.coon.myblogspringboot.dto.ReplySaveRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;
    @ManyToOne //여러개의 댓글은 하나의 게시글에 존재가능
    @JoinColumn(name = "boardId")
    private Board board;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @CreationTimestamp
    private Timestamp createDate;

//    public void update(User user, Board board, String content){
//        setUser(user);
//        setBoard(board);
//        setContent(content);
//
//    }
}
