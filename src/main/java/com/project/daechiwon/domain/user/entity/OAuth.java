package com.project.daechiwon.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Builder
@Entity
public class OAuth {
    @EmbeddedId
    private OAuthId oAuthId;

    @Column(nullable = false, columnDefinition = "VARCHAR(512)")
    private String openId;
}
