package com.project.daechiwon.domain.user.service;

import com.project.daechiwon.domain.user.entity.OAuth;
import com.project.daechiwon.domain.user.entity.OAuthId;
import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.exception.AuthFailedException;
import com.project.daechiwon.domain.user.repository.UserRepository;
import com.project.daechiwon.domain.user.type.OAuthProvider;
import com.project.daechiwon.domain.user.type.UserType;
import com.project.daechiwon.global.infra.google.GoogleApiService;
import com.project.daechiwon.global.infra.google.GoogleAuthProperties;
import com.project.daechiwon.global.infra.google.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;

@Log4j2
@RequiredArgsConstructor
@Service
public class GoogleOAuthService implements OAuthService {

    private final GoogleAuthService googleAuthService;
    private final GoogleApiService googleApiService;
    private final GoogleAuthProperties googleAuthProperties;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User authorize(String code) {
        GoogleAuthService.GetAccessTokenResponse accessTokenResponse = getAccessTokenByCode(code);
        log.info(accessTokenResponse.toString());
        GoogleApiService.GetUserInfoResponse userInfo = getUserInfoByAccessToken(accessTokenResponse.getAccessToken());
        log.info(userInfo.toString());

        return userRepository.findByOpenId(userInfo.getOpenId(), OAuthProvider.GOOGLE.name())
                .orElseGet(() -> joinOAuthUser(userInfo));
    }

    private GoogleAuthService.GetAccessTokenResponse getAccessTokenByCode(String code) {
        try {
            Response<GoogleAuthService.GetAccessTokenResponse> tokenRes = googleAuthService.getAccessToken(
                            code, googleAuthProperties.getClientId(), googleAuthProperties.getClientSecret(),
                            "authorization_code", googleAuthProperties.getClientRedirect())
                    .execute();
            if(!tokenRes.isSuccessful()) {
                log.error(tokenRes.code());
                throw new AuthFailedException();
            }
            return tokenRes.body();
        }catch (IOException ex) {
            ex.printStackTrace();
            throw new AuthFailedException();
        }
    }

    private GoogleApiService.GetUserInfoResponse getUserInfoByAccessToken(String accessToken) {
        try {
            Response<GoogleApiService.GetUserInfoResponse> infoRes = googleApiService
                    .getInfo(String.format("Bearer %s", accessToken))
                    .execute();
            if(!infoRes.isSuccessful()) {
                assert infoRes.errorBody() != null;
                log.error(new String(infoRes.errorBody().bytes()));
                throw new AuthFailedException();
            }
            return infoRes.body();
        }catch (IOException ex) {
            ex.printStackTrace();
            throw new AuthFailedException();
        }
    }

    @Transactional
    public User joinOAuthUser(GoogleApiService.GetUserInfoResponse userInfo) {
        User newUser = User.builder()
                .nickname(userInfo.getGivenName())
                .type(UserType.NORMAL)
                .oAuthList(new ArrayList<>())
                .build();

        OAuth oAuth = OAuth.builder()
                .oAuthId(OAuthId.builder()
                        .user(newUser)
                        .provider(OAuthProvider.GOOGLE)
                        .build())
                .openId(userInfo.getOpenId())
                .build();
        newUser.getOAuthList().add(oAuth);

        return userRepository.save(newUser);
    }
}
