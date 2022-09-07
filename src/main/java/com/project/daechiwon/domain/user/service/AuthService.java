package com.project.daechiwon.domain.user.service;

import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.exception.UserDuplicatedException;
import com.project.daechiwon.domain.user.exception.UserNotFoundException;
import com.project.daechiwon.domain.user.exception.UserUnauthorizedException;
import com.project.daechiwon.domain.user.exception.WrongProviderException;
import com.project.daechiwon.domain.user.presentation.dto.request.SignInRequest;
import com.project.daechiwon.domain.user.presentation.dto.request.SignUpRequest;
import com.project.daechiwon.domain.user.repository.UserRepository;
import com.project.daechiwon.domain.user.type.OAuthProvider;
import com.project.daechiwon.domain.user.type.UserType;
import com.project.daechiwon.global.security.UserAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Qualifier("googleOAuthService")
    private final OAuthService googleOAuth;

    @Transactional(readOnly = true)
    public void signIn(SignInRequest request) {
        User user = userRepository.findByLoginId(request.getLoginId())
                .orElseThrow(UserUnauthorizedException::new);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new UserUnauthorizedException();

        Authentication authentication = new UserAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Transactional
    public void signUp(SignUpRequest request) {
        if(userRepository.findByLoginId(request.getLoginId()).isPresent())
            throw new UserDuplicatedException();

        User user = User.builder()
                .loginId(request.getLoginId())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .oAuthList(new ArrayList<>())
                .type(request.getType())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public void oAuth(String code, String oAuthProviderName) {
        OAuthProvider provider = OAuthProvider.fromName(oAuthProviderName)
                .orElseThrow(WrongProviderException::new);

        User user;
        switch (provider) {
            case GOOGLE:
                user = googleOAuth.authorize(code);
                break;
            default:
                user = null;
        }
        if(user == null) throw new UserNotFoundException();

        Authentication authentication = new UserAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
