package com.app.oudiac.repositories;


import com.app.oudiac.dtos.userDtos.UserRegisterResponseDto;
import com.app.oudiac.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}