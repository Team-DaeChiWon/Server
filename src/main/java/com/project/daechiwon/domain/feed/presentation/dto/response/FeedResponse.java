package com.project.daechiwon.domain.feed.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
@Builder
public class FeedResponse {

    private Long feedId;

    private String title;

    private String content;

    private LocalDateTime createAt;

    private Long authorId;

    private Long communityId;

}
