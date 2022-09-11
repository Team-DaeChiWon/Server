package com.project.daechiwon.domain.community.entity;

import com.project.daechiwon.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityId;

    // 카페 이름
    @Column(nullable = false)
    private String communityName;

    // 카페 설명
    @Lob
    private String communityExplain;

    // 카페 설립일자
    @CreationTimestamp
    private LocalDateTime createAt;

    // 카페 설립자
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    // 카페 회원들
    @OneToMany(mappedBy = "community", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CommunityUser> communityUserList;
    public void addUser(CommunityUser communityUser) {
        this.communityUserList.add(communityUser);
    }
}
