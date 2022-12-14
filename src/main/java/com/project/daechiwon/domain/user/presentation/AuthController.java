package com.project.daechiwon.domain.user.presentation;

import com.project.daechiwon.domain.user.presentation.dto.request.SignInRequest;
import com.project.daechiwon.domain.user.presentation.dto.request.SignUpRequest;
import com.project.daechiwon.domain.user.presentation.dto.response.UserResponse;
import com.project.daechiwon.domain.user.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "유저")
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @ApiOperation("유저가 로그인을 합니다")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/sign-in")
    public void signIn(@Valid @RequestBody SignInRequest request) {
        authService.signIn(request);
    }

    @ApiOperation("유저가 회원가입을 합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest request) {
        authService.signUp(request);
    }

    @ApiOperation("Google로 로그인을 합니다")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/oauth/{provider-name}")
    public void oAuth(@RequestParam("code") String code, @PathVariable("provider-name") String oAuthProviderName) {
        authService.oAuth(code, oAuthProviderName);
    }

    @ApiOperation("유저의 프로필을 조회합니다")
    @GetMapping("/")
    public UserResponse getUserProfile() {
        return authService.getUserProfile();
    }

}
