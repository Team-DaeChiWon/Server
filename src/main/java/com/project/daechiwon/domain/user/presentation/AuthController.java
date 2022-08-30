package com.project.daechiwon.domain.user.presentation;

import com.project.daechiwon.domain.user.presentation.dto.request.SignInRequest;
import com.project.daechiwon.domain.user.presentation.dto.request.SignUpRequest;
import com.project.daechiwon.domain.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/sign-in")
    public void signIn(@Valid @RequestBody SignInRequest request) {
        authService.signIn(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest request) {
        authService.signUp(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/oauth/{provider-name}")
    public void oAuth(@RequestParam("code") String code, @PathVariable("provider-name") String oAuthProviderName) {
        authService.oAuth(code, oAuthProviderName);
    }

}
