package com.project.daechiwon.domain.feed.presentation;

import com.project.daechiwon.domain.feed.presentation.dto.request.CreateFeedRequest;
import com.project.daechiwon.domain.feed.presentation.dto.response.FeedResponse;
import com.project.daechiwon.domain.feed.service.FeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "게시글")
@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedController {

    private final FeedService feedService;

    @ApiOperation("게시글을 생성합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{community-id}")
    public void createFeed(
            @PathVariable("community-id") Long communityId,
            @RequestBody CreateFeedRequest request
    ) {
        feedService.createFeed(communityId, request);
    }

    @ApiOperation("게시글 정보를 가지고 옵니다")
    @GetMapping("/")
    public FeedResponse getFeed() {
        return feedService.getFeedByUser();
    }

    @ApiOperation("게시글을 삭제합니다")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-id}")
    public void deleteFeed(
            @PathVariable("feed-id") Long feedId
    ) {
        feedService.deleteFeed(feedId);
    }

}
