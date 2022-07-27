package com.webmister.semicolon.dto;

import com.webmister.semicolon.domain.Report;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ReportResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime reportCreateTime, reportUpdateTime;
    private Long userId;
    private List<EssentialComment> comments;

    public ReportResponse(Report report) {
        this.id = report.getReportId();
        this.title = report.getTitle();
        this.content = report.getContents();
        this.reportCreateTime = report.getReportCreateTime();
        this.reportUpdateTime = report.getReportUpdateTime();
        this.userId = report.getUserInfo().getUserInfoId();
        this.comments = report.getComments().stream().map(EssentialComment::new).collect(Collectors.toList());    }

}
