package com.project.daechiwon.domain.user.entity;

import com.project.daechiwon.domain.user.type.OAuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter
@Embeddable
public class OAuthId implements Serializable {
    @JoinColumn
    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private OAuthProvider provider;
}
