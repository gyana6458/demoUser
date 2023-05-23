package com.demo.repository;

import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);//this will find username&email
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);//this will check user exist or not
    Boolean existsByEmail(String email);

}