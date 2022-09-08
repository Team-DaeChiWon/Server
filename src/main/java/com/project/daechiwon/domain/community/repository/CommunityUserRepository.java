package com.project.daechiwon.domain.community.repository;

import com.project.daechiwon.domain.community.entity.CommunityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityUserRepository extends CrudRepository<CommunityUser, Long> {
}
