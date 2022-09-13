package com.project.daechiwon.domain.notification.service;

import com.project.daechiwon.domain.community.entity.Community;
import com.project.daechiwon.domain.community.entity.CommunityUser;
import com.project.daechiwon.domain.community.exception.CommunityNotFoundException;
import com.project.daechiwon.domain.community.repository.CommunityRepository;
import com.project.daechiwon.domain.notification.entity.Notification;
import com.project.daechiwon.domain.notification.exception.NotificationAlreadyExistsException;
import com.project.daechiwon.domain.notification.exception.NotificationNotFoundException;
import com.project.daechiwon.domain.notification.presentation.dto.request.CreateNotificationRequest;
import com.project.daechiwon.domain.notification.presentation.dto.response.NotificationResponse;
import com.project.daechiwon.domain.notification.repository.NotificationRepository;
import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.exception.UserUnauthorizedException;
import com.project.daechiwon.domain.user.facade.UserFacade;
import com.project.daechiwon.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private CommunityRepository communityRepository;
    private NotificationRepository notificationRepository;
    private UserFacade userFacade;

    @Transactional
    public Long createNotification(Long communityId, CreateNotificationRequest request) {

        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        notificationRepository.findByContent(request.getContent())
                .ifPresent(m -> {
                    throw new NotificationAlreadyExistsException();
                });

        Community community = communityRepository.findById(communityId)
                .orElseThrow(CommunityNotFoundException::new);

        Notification notification = Notification.builder()
                .content(request.getContent())
                .community(community)
                .author(user)
                .build();
        community.getNotificationList().add(notification);
        user.getNotificationList().add(notification);

        return notification.getNotificationId();
    }

    @Transactional(readOnly = true)
    public NotificationResponse getNotificationById(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(NotificationNotFoundException::new);

        return NotificationResponse.builder()
                .notificationId(notification.getNotificationId())
                .content(notification.getContent())
                .createAt(notification.getCreateAt())
                .communityId(notification.getCommunity().getCommunityId())
                .authorId(notification.getAuthor().getId())
                .build();
    }

}
