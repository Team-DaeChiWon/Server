package com.project.daechiwon.domain.community.entity;

import com.project.daechiwon.domain.user.entity.User;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityUserId implements Serializable {

    private Community community;

    private User user;

}
