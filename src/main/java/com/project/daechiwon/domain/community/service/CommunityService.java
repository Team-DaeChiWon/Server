package com.project.daechiwon.domain.community.service;

import com.project.daechiwon.domain.community.entity.Community;
import com.project.daechiwon.domain.community.entity.CommunityUser;
import com.project.daechiwon.domain.community.exception.CommunityAccessDeniedException;
import com.project.daechiwon.domain.community.exception.CommunityAlreadyExistsException;
import com.project.daechiwon.domain.community.exception.CommunityNotFoundException;
import com.project.daechiwon.domain.community.presentation.dto.request.CreateCommunityRequest;
import com.project.daechiwon.domain.community.presentation.dto.response.CommunityResponse;
import com.project.daechiwon.domain.community.repository.CommunityRepository;
import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.exception.UserUnauthorizedException;
import com.project.daechiwon.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UserFacade userFacade;

    @Transactional
    public void createCommunity(CreateCommunityRequest request) {

        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        communityRepository.findByCommunityName(request.getCommunityName())
                .ifPresent(m -> {
                    throw new CommunityAlreadyExistsException();
                });

        Community community = Community.builder()
                .communityName(request.getCommunityName())
                .communityExplain(request.getCommunityExplain())
                .owner(user)
                .build();
        community = communityRepository.save(community);

        CommunityUser communityUser = CommunityUser.builder()
                .community(community)
                .user(user)
                .build();

        user.addCommunity(communityUser);
    }

    @Transactional(readOnly = true)
    public CommunityResponse getCommunityById(Long id) {

        Community community = communityRepository.findById(id)
                .orElseThrow(CommunityNotFoundException::new);

        return CommunityResponse.builder()
                .communityId(community.getCommunityId())
                .communityName(community.getCommunityName())
                .communityExplain(community.getCommunityExplain())
                .ownerId(community.getOwner().getId())
                .createAt(community.getCreateAt())
                .build();
    }

    @Transactional
    public void deleteCommunity(Long id) {

        User owner = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        Community community = communityRepository.findById(id)
                .orElseThrow(CommunityNotFoundException::new);

        if(!community.getOwner().equals(owner))
            throw new CommunityAccessDeniedException();

        communityRepository.delete(community);
    }

}