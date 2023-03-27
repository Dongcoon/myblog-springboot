package com.coon.myblogspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String title;
    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리<html>태그가 섞여서 디자인이 됨.
    private int count; //조회수
    @ManyToOne(fetch = FetchType.EAGER) //Many=Board, User=One
    @JoinColumn(name = "userId")
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER,cascade = CascadeType.REMOVE) //하나의 게시글에 여러개의 댓글
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    // mappedBy 연관관계의 주인이 아니다 (FK가 아님) DB에 컬럼 만들면 안됨.
    //@JoinColumn -> 원자성 위배로 필요없음. >> Reply에 Board 존재
    private List<Reply> replys;
    @CreationTimestamp
    private Timestamp createTime;
}
