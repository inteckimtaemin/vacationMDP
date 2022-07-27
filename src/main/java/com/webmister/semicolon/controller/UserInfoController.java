package com.webmister.semicolon.controller;

import com.webmister.semicolon.domain.UserInfo;
import com.webmister.semicolon.request.DeleteUserRequest;
import com.webmister.semicolon.request.FindUserOnlyOneRequest;
import com.webmister.semicolon.response.FindUserOnlyOneResponse;
import com.webmister.semicolon.request.UserInfoRequest;
import com.webmister.semicolon.response.FindUserOnlyOneResponse;
import com.webmister.semicolon.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

//    @PostMapping("/studio/{studioId}/label")
//    public ResponseEntity<EssentialUserInfo> createLabel(@PathVariable("studioId") Long studioId , @RequestBody UserInfoRequest userInfoRequest){
//        UserInfo userInfo = userInfoService.signUp(userInfoRequest);
//
//        return new ResponseEntity<>( new EssentialUserInfo(userInfo), this.makeUTF8Header(),  HttpStatus.OK);
//    }
//@PostMapping("/studio/{studioId}/label")
//public ResponseEntity<LabelInfoDTO> createLabel(@PathVariable("studioId") Long studioId , @RequestBody LabelRequest labelRequest){
//    Label label = labelService.createStudioLabel(studioId, labelRequest);
//
//    return new ResponseEntity<>( new LabelInfoDTO(label), this.makeUTF8Header(),  HttpStatus.OK);
//}

    @PostMapping("/")
    public ResponseEntity<List<UserInfo>> aaaaa(){
        List<UserInfo> userInfoList;

        userInfoList = userInfoService.findAll();

        System.out.println(userInfoList);

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(userInfoList ,resHeaders,  HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<FindUserOnlyOneResponse> createUser(@RequestBody FindUserOnlyOneRequest findUserOnlyOneRequest){
        UserInfo userInfo = userInfoService.findUserInfoById(findUserOnlyOneRequest.getId());

        FindUserOnlyOneResponse findUserOnlyOneResponse = new FindUserOnlyOneResponse(userInfo);

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(findUserOnlyOneResponse ,resHeaders,  HttpStatus.OK);
    }
    @GetMapping("/{userNickname}")
    public ResponseEntity<FindUserOnlyOneResponse> createUser(@PathVariable("userNickname") String userNickname,
                                                              @RequestBody FindUserOnlyOneRequest findUserOnlyOneRequest){
        UserInfo user1 = userInfoService.findUserInfoByUserNickname(userNickname);

        FindUserOnlyOneResponse findUserOnlyOneResponse = new FindUserOnlyOneResponse(user1);

        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(findUserOnlyOneResponse ,resHeaders,  HttpStatus.OK);
    }
    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(@RequestBody UserInfoRequest userInfoRequest) {
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        try {
        if ( !userInfoService.checkDupicateUserNickname(userInfoRequest.getUserNickName()) &
                !userInfoService.checkDupicateEmail(userInfoRequest.getUserEmail()))
                userInfoService.signUp(userInfoRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(Boolean.FALSE, resHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Boolean.TRUE, resHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/userDelete")
    public ResponseEntity<Boolean> reportDelete(@RequestBody DeleteUserRequest deleteUserRequest){
        HttpHeaders resHeaders = new HttpHeaders();
        resHeaders.add("Content-Type", "application/json;charset=UTF-8");

        try {
            userInfoService.deleteUser(deleteUserRequest);
        }catch (Exception e){
            return new ResponseEntity<>(Boolean.FALSE, resHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(Boolean.TRUE, resHeaders, HttpStatus.OK);
    }
}
