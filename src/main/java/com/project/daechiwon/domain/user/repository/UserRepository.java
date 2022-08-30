package com.project.daechiwon.domain.user.repository;

import com.project.daechiwon.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user JOIN oauth WHERE open_id=?1 AND provider=?2", nativeQuery = true)
    Optional<User> findByOpenId(String openId, String provider);

    Optional<User> findByLoginId(String loginId);
}
