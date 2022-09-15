package com.project.daechiwon.domain.feed.repository;

import com.project.daechiwon.domain.feed.entity.Feed;
import com.project.daechiwon.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

    Optional<Feed> findByAuthor(User author);

}
