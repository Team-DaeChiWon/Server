package com.project.daechiwon.domain.notification.presentation;

import com.project.daechiwon.domain.notification.presentation.dto.request.CreateNotificationRequest;
import com.project.daechiwon.domain.notification.presentation.dto.response.NotificationResponse;
import com.project.daechiwon.domain.notification.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "알림장")
@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    @ApiOperation("알림장을 작성합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{community-id}")
    public Long createNotification(
            @PathVariable("community-id") Long communityId,
            @RequestBody CreateNotificationRequest request
    ) {
        return notificationService.createNotification(communityId, request);
    }

    @ApiOperation("알림장의 정보를 가지고 옵니다")
    @GetMapping("/{notification-id}")
    public NotificationResponse getNotification(
            @PathVariable("notification-id") Long notificationId
    ) {
        return notificationService.getNotificationById(notificationId);
    }

}
