package com.webmister.semicolon.service;

import com.webmister.semicolon.domain.Report;
import com.webmister.semicolon.domain.UserInfo;
import com.webmister.semicolon.repository.ReportRepository;
import com.webmister.semicolon.repository.UserInfoRepository;
import com.webmister.semicolon.request.DeleteReportRequest;
import com.webmister.semicolon.request.FindReportOnlyOneRequest;
import com.webmister.semicolon.request.UploadRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    UserInfoRepository userInfoRepository;


    public Boolean reportUpload(UploadRequest uploadRequest){
        try{
            UserInfo userInfo = userInfoRepository.findById(uploadRequest.getUserId()).get();
            reportRepository.save(Report.builder()
                    .title(uploadRequest.getTitle())
                    .contents(uploadRequest.getContents())
                    .likeCount(uploadRequest.getLikeCount())
                    .reportImageUrl(uploadRequest.getReportImageUrl())
                    .userInfo(userInfo)
                    .build());
            return Boolean.TRUE;
        }catch (Exception e){
            return Boolean.FALSE;
        }
    }

    public Report findByReportId(Long id){
        return reportRepository.findById(id).orElse(new Report());
    }

//    public Report findReportByReportId(Long reportId, FindReportOnlyOneRequest findReportOnlyOneRequest){
//        userInfoRepository.findById(findReportOnlyOneRequest.getUserId());
//
//        return reportRepository.
//    }

    public Report findReportByUserIdAndReportId(Long userId, Long reportId){
        UserInfo userInfo = userInfoRepository.findById(userId).get();
        List<Report> reportList =  userInfo.getReportList();
        Report report1 = new Report();
        for (Report report : reportList){
            if(report.getReportId() == reportId){
                report1 = report;
                break;
            }
        }
        return report1;
    }

    public Boolean deleteReport(DeleteReportRequest reportId){
        try{
            reportRepository.deleteById(reportId.getReportId());
        }catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
