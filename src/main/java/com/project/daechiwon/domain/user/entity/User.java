package com.project.daechiwon.domain.user.entity;

import com.project.daechiwon.domain.community.entity.Community;
import com.project.daechiwon.domain.community.entity.CommunityUser;
import com.project.daechiwon.domain.notification.entity.Notification;
import com.project.daechiwon.domain.plan.entity.EducationPlan;
import com.project.daechiwon.domain.user.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Entity @Builder
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String loginId;

    @Column(columnDefinition = "VARCHAR(512)")
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType type;

    @CreationTimestamp
    private LocalDateTime registeredAt;

    @OneToMany(mappedBy = "oAuthId.user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OAuth> oAuthList;

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST})
    private List<EducationPlan> planList;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST})
    private List<Community> communityList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommunityUser> communityUserList = new ArrayList<>();
    public void addCommunity(CommunityUser communityUser) {
        this.communityUserList.add(communityUser);
    }

    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST})
    private List<Notification> notificationList;

}