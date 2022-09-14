package com.project.daechiwon.domain.community.entity;

import com.project.daechiwon.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@IdClass(CommunityUserId.class)
public class CommunityUser {

    @Id
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
