package com.app.oudiac.dtos.userDtos;

import com.app.oudiac.models.User;
import com.app.oudiac.models.enums.Role;
import lombok.Data;

@Data
public class UserInfoDto {

    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    private Role role;

    public static UserInfoDto fromUserToUserInfoDto(User user) {
        UserInfoDto userInfoDto=new UserInfoDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setName(user.getName());
        userInfoDto.setMobileNumber(user.getMobileNumber());
        userInfoDto.setRole(user.getRole());

        return  userInfoDto;
    }
}
