package com.project.daechiwon.domain.user.service;

import com.project.daechiwon.domain.user.entity.User;

public interface OAuthService {
    User authorize(String code);
}
