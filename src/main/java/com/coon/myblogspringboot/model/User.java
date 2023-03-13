package com.coon.myblogspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity //User 클래스가 db에 테이블이 생성
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링(auto-increment) 전략을 따라간다.
    private int id;
    @Column(nullable = false, length = 30)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;
    @ColumnDefault("'user'")
    private String role; //Enum을 쓰는게 좋다. // admin, user, manager
    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
