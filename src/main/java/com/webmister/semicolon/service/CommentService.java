package com.webmister.semicolon.service;

import com.webmister.semicolon.domain.Comment;
import com.webmister.semicolon.domain.Report;
import com.webmister.semicolon.domain.UserInfo;
import com.webmister.semicolon.repository.CommentRepository;
import com.webmister.semicolon.repository.ReportRepository;
import com.webmister.semicolon.repository.UserInfoRepository;
import com.webmister.semicolon.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserInfoRepository userInfoRepository;
    public Boolean createComment(CommentRequest commentRequest)  {
        try{
            Report report = reportRepository.findById(commentRequest.getReportId()).get();
            UserInfo userInfo = userInfoRepository.findById(commentRequest.getUserId()).get();
            commentRepository.save(Comment.builder()
                    .comment(commentRequest.getComment())
                    .userInfo(userInfo)
                    .report(report)
                    .build());
        }catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Comment findCommentById(Long id){
        return commentRepository.findById(id).orElse(new Comment());
    }

}
