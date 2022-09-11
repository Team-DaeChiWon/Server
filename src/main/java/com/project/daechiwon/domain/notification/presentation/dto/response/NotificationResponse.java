package com.project.daechiwon.domain.notification.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long notificationId;

    private String content;

    private LocalDateTime createAt;

    private Long communityId;

    private Long authorId;

}
