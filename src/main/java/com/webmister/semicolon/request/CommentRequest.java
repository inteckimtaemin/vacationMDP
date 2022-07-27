package com.webmister.semicolon.request;

import com.webmister.semicolon.domain.Report;
import com.webmister.semicolon.domain.UserInfo;
import lombok.Data;

@Data
public class CommentRequest {
    private String comment;
    private Long userId;
    private Long reportId;
}
