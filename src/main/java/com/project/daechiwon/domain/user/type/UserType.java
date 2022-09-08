package com.project.daechiwon.domain.user.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public enum UserType {
    NORMAL, TEACHER;

    @JsonCreator
    public static UserType fromTestEnum(String val){
        for(UserType userType : UserType.values()){
            if(userType.name().equals(val)){
                return userType;
            }
        }

        throw new BusinessException(HttpStatus.BAD_REQUEST, "There's no qualified enum's name :(");
    }
}
