package com.sparta.springcore.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor  // 생성자를 대신해준다
public class KakaoUserInfoDto {
    private Long id;
    private String nickname;
    private String email;


}
