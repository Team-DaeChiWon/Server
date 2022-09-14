package com.project.daechiwon.domain.community.entity;

import lombok.*;

import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityUserId implements Serializable {

    private Long community;

    private Long user;

}
