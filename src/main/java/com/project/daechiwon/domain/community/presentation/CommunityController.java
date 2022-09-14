package com.project.daechiwon.domain.community.presentation;

import com.project.daechiwon.domain.community.presentation.dto.request.CreateCommunityRequest;
import com.project.daechiwon.domain.community.presentation.dto.response.CommunityResponse;
import com.project.daechiwon.domain.community.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "커뮤니티")
@RestController
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    @ApiOperation("커뮤니티를 생성합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void createCommunity(
            @RequestBody CreateCommunityRequest request
    ) {
        communityService.createCommunity(request);
    }

    @ApiOperation("커뮤니티 id로 정보를 가지고 옵니다")
    @GetMapping("/{id}")
    public CommunityResponse getCommunityById(
        @PathVariable("id") Long id
    ) {
        return communityService.getCommunityById(id);
    }

    @ApiOperation("커뮤니티를 삭제합니다")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCommunity(
            @PathVariable("id") Long id
    ) {
        communityService.deleteCommunity(id);
    }
}
