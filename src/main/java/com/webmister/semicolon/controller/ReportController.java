package com.webmister.semicolon.controller;

import com.webmister.semicolon.domain.Report;
import com.webmister.semicolon.domain.UserInfo;
import com.webmister.semicolon.repository.ReportRepository;
import com.webmister.semicolon.request.DeleteReportRequest;
import com.webmister.semicolon.request.FindReportOnlyOneRequest;
import com.webmister.semicolon.request.FindUserOnlyOneRequest;
import com.webmister.semicolon.request.UploadRequest;
import com.webmister.semicolon.response.FindReportOnlyOneResponse;
import com.webmister.semicolon.response.FindUserOnlyOneResponse;
import com.webmister.semicolon.service.CommentService;
import com.webmister.semicolon.service.ReportService;
import com.webmister.semicolon.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/reportUpload")
    public ResponseEntity<Boolean> reportUpload(@RequestBody UploadRequest uploadRequest){
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        try {
            reportService.reportUpload(uploadRequest);
        }catch (Exception e){
            return new ResponseEntity<>(Boolean.FALSE, resHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Boolean.TRUE, resHeaders, HttpStatus.OK);
    }

    @GetMapping("/{userNickname}/{reportId}")
    public ResponseEntity<FindReportOnlyOneResponse> createUser(@PathVariable("userNickname") String userNickname,@PathVariable("reportId") Long reportId ,
                                                              @RequestBody FindReportOnlyOneRequest findReportOnlyOneRequest){
        UserInfo userInfo = userInfoService.findUserInfoByUserNickname(userNickname);
        Report report1 = reportService.findReportByUserIdAndReportId(userInfo.getUserInfoId(), reportId);


        FindReportOnlyOneResponse findReportOnlyOneResponse = new FindReportOnlyOneResponse(report1);

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(findReportOnlyOneResponse ,resHeaders,  HttpStatus.OK);
    }

    @DeleteMapping("/reportDelete")
    public ResponseEntity<Boolean> reportDelete(@RequestBody DeleteReportRequest deleteReportRequest){
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        try {
            reportService.deleteReport(deleteReportRequest);
        }catch (Exception e){
            return new ResponseEntity<>(Boolean.FALSE, resHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Boolean.TRUE, resHeaders, HttpStatus.OK);
    }
}
