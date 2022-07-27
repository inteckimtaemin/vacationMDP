package com.webmister.semicolon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id
    @Column(name = "userInfoId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userInfoId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userNickName;

    @Column(nullable = false)
    private LocalDateTime userInfoCreateDate;

    @Column(nullable = false)
    private String userUniqueID;

    @Column
    private String userProfileImageUrl;

    @Column
    private String userDescription;

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Report> reportList = new ArrayList<Report>();

    @OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<Comment>();


    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }
    @PrePersist
    public void UserInfoCreatDate(){
        this.userInfoCreateDate = LocalDateTime.now();
    }

}

