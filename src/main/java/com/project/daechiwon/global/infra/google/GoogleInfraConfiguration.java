package com.project.daechiwon.global.infra.google;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class GoogleInfraConfiguration {
    @Bean
    public GoogleAuthService googleAuthService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://oauth2.googleapis.com")
                .build();
        return retrofit.create(GoogleAuthService.class);
    }

    @Bean
    public GoogleApiService googleApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://www.googleapis.com")
                .build();
        return retrofit.create(GoogleApiService.class);
    }
}
