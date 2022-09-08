package com.project.daechiwon.domain.community.service;

import com.project.daechiwon.domain.community.entity.Community;
import com.project.daechiwon.domain.community.entity.CommunityUser;
import com.project.daechiwon.domain.community.exception.CommunityAccessDeniedException;
import com.project.daechiwon.domain.community.exception.CommunityAlreadyExistsException;
import com.project.daechiwon.domain.community.exception.CommunityNotFoundException;
import com.project.daechiwon.domain.community.presentation.dto.request.CreateCommunityRequest;
import com.project.daechiwon.domain.community.presentation.dto.response.CommunityResponse;
import com.project.daechiwon.domain.community.repository.CommunityRepository;
import com.project.daechiwon.domain.community.repository.CommunityUserRepository;
import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.exception.UserUnauthorizedException;
import com.project.daechiwon.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final CommunityUserRepository communityUserRepository;
    private final UserFacade userFacade;

    @Transactional
    public Long createCommunity(CreateCommunityRequest request) {

        User user = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        communityRepository.findByCommunityName(request.getCommunityName())
                .ifPresent(m -> {
                    throw new CommunityAlreadyExistsException();
                });

        Community community = Community.builder()
                .communityName(request.getCommunityName())
                .explain(request.getCommunityExplain())
                .number(1)
                .author(user)
                .build();
        user.getCommunityUserList().add(new CommunityUser(community, user));

        return communityRepository.save(community).getCommunityId();
    }

    @Transactional(readOnly = true)
    public CommunityResponse getCommunityById(Long id) {

        Community community = communityRepository.findById(id)
                .orElseThrow(CommunityNotFoundException::new);

        return CommunityResponse.builder()
                .communityId(community.getCommunityId())
                .communityName(community.getCommunityName())
                .communityExplain(community.getExplain())
                .createAt(community.getCreateAt())
                .build();
    }

    @Transactional
    public void deleteCommunity(Long id) {

        User author = userFacade.queryUser(true)
                .orElseThrow(UserUnauthorizedException::new);

        Community community = communityRepository.findById(id)
                .orElseThrow(CommunityNotFoundException::new);

        if(!community.getAuthor().equals(author))
            throw new CommunityAccessDeniedException();

        author.getCommunityUserList().remove(new CommunityUser(community, author));
        communityRepository.delete(community);
    }

}
