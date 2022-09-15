package com.project.daechiwon.domain.feed.entity;

import com.project.daechiwon.domain.community.entity.Community;
import com.project.daechiwon.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    private String title;

    @Lob
    private String content;

    @CreationTimestamp
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

}
