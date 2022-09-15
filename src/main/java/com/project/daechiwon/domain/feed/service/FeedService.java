package com.project.daechiwon.domain.feed.service;

import com.project.daechiwon.domain.community.entity.Community;
import com.project.daechiwon.domain.community.exception.CommunityNotFoundException;
import com.project.daechiwon.domain.community.repository.CommunityRepository;
import com.project.daechiwon.domain.feed.entity.Feed;
import com.project.daechiwon.domain.feed.exception.FeedAcceessDeniedException;
import com.project.daechiwon.domain.feed.exception.FeedNotFoundException;
import com.project.daechiwon.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.project.daechiwon.domain.feed.presentation.dto.response.FeedResponse;
import com.project.daechiwon.domain.feed.repository.FeedRepository;
import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.exception.UserUnauthorizedException;
import com.project.daechiwon.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FeedService {

    private final FeedRepository feedRepository;
    private final CommunityRepository communityRepository;
    private final UserFacade userFacade;

    @Transactional
    public void createFeed(Long communityId, CreateFeedRequest request) {

        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        Community community = communityRepository.findById(communityId)
                .orElseThrow(CommunityNotFoundException::new);

        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user)
                .community(community)
                .build();
        community.addFeed(feed);

    }

    @Transactional(readOnly = true)
    public FeedResponse getFeedByUser() {

        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        Feed feed = feedRepository.findByAuthor(user)
                .orElseThrow(FeedNotFoundException::new);

        return FeedResponse.builder()
                .feedId(feed.getFeedId())
                .title(feed.getTitle())
                .content(feed.getContent())
                .createAt(feed.getCreateAt())
                .authorId(feed.getAuthor().getId())
                .communityId(feed.getCommunity().getCommunityId())
                .build();
    }

    @Transactional
    public void deleteFeed(Long feedId) {

        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(FeedNotFoundException::new);

        if(!feed.getAuthor().equals(user)) {
            throw new FeedAcceessDeniedException();
        }

        feedRepository.delete(feed);
    }

}
