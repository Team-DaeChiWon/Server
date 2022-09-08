package com.project.daechiwon.domain.user.type;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    NORMAL, TEACHER

    @JsonCreator
    public static UserType fromTestEnum(String val){
        for(UserType userType : UserType.values()){
            if(userType.name().equals(val)){
                return userType;
            }
        }
        return null;
    }
}
