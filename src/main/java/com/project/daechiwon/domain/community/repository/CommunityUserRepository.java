package com.project.daechiwon.domain.community.repository;

import com.project.daechiwon.domain.community.entity.CommunityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityUserRepository extends JpaRepository<CommunityUser, Long> {
}
