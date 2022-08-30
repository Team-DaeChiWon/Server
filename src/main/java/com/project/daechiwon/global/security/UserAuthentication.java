package com.project.daechiwon.global.security;

import com.project.daechiwon.domain.user.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {
    public UserAuthentication(User user) {
        super(user, null, List.of((GrantedAuthority) () -> user.getType().name()));
    }
}
