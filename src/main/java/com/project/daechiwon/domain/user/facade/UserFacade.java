package com.project.daechiwon.domain.user.facade;

import com.project.daechiwon.domain.user.entity.User;
import com.project.daechiwon.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public Optional<User> queryUser() {
        return queryUser(false);
    }

    @Transactional(readOnly = true)
    public Optional<User> queryUser(boolean withPersistence) {
        User withoutPersistence = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(withPersistence) {
            return userRepository.findById(withoutPersistence.getId());
        } else {
            return Optional.ofNullable(withoutPersistence);
        }
    }

}
