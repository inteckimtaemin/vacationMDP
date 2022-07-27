package com.webmister.semicolon.dto;

import com.webmister.semicolon.domain.UserInfo;
import lombok.Data;
import org.apache.catalina.User;

import java.sql.Timestamp;

@Data
public class EssentialUserInfo {

    String password;
    String userEmail;
    String userNickName;
    String userUniqueId;
    String userProfileImageUrl;
    String userDescription;

    public EssentialUserInfo(UserInfo userInfo) {
        this.userEmail = getUserEmail();
        this.password = getPassword();
        this.userNickName = getUserNickName();
        this.userUniqueId = getUserUniqueId();

        if(userInfo.getUserProfileImageUrl() != null) {
            this.userProfileImageUrl = userInfo.getUserProfileImageUrl();
        }

        if(userInfo.getUserDescription() != null) {
            this.userDescription = userInfo.getUserDescription();
        }


    }
}
