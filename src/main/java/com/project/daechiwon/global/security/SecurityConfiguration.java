package com.project.daechiwon.global.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Log4j2
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Security configuration loaded");

        http
                // 폼 로그인 비활성화
                .formLogin().disable()

                // csrf 비활성화
                .csrf().disable()

                // 세션 관리
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS).and()

                // auth
                .authorizeRequests().antMatchers("/auth/**").permitAll().and()

                // 계획
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/plans/").permitAll()
                .antMatchers(HttpMethod.POST, "/plans/").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.PATCH, "/plans/**").hasAuthority("TEACHER")
                .antMatchers(HttpMethod.DELETE, "/plans/**").hasAuthority("TEACHER")
                .and()

                // 별도로 지정하지 아니한 경로는 모두 접근 거부
                .authorizeRequests().anyRequest().denyAll();
    }
}
