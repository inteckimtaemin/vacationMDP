package com.webmister.semicolon.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @Column(name = "commentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(name = "createdDate")
    private LocalDateTime commentCreateTime;

    @Column(name = "updateDate")
    private LocalDateTime commentUpdateTime;

    @ManyToOne
    @JoinColumn(name = "reportId")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "userInfoId")
    private UserInfo userInfo;

    @PrePersist
    public void CommentCreatedDate() {
        this.commentCreateTime = LocalDateTime.now();
    }

    public void CommentUpdatedDate() {
        this.commentUpdateTime = LocalDateTime.now();
    }
}
