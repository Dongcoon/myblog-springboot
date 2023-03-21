package com.coon.myblogspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 db에 테이블이 생성
//@DynamicInsert insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링(auto-increment) 전략을 따라간다.
    private int id;
    @Column(nullable = false, length = 30, unique = true)
    private String username;
    @Column(nullable = false, length = 100)
    private String password;
    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
//    @ColumnDefault("user")
    private RoleType role; //Enum을 쓰는게 좋다. // ADMIN, USER
    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;
}
