package com.app.oudiac.utils;

import ch.qos.logback.core.util.StringUtil;
import com.app.oudiac.dtos.userDtos.UserRegisterRequestDto;
import com.app.oudiac.exceptions.BlankFieldException;

public class FieldValidator {

    public static void validateUserFields(UserRegisterRequestDto requestDto){

        if(StringUtil.isNullOrEmpty(requestDto.getName())){
            throw new BlankFieldException("Please enter all details!!");
        }

        if(StringUtil.isNullOrEmpty(requestDto.getEmail())){
            throw new BlankFieldException("Please enter all details!!");
        }
    }
}
