package com.project.daechiwon.domain.community.entity;

import com.project.daechiwon.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapId;

    @ManyToOne
    @JoinColumn
    private Community community;

    @ManyToOne
    @JoinColumn
    private User user;

}
