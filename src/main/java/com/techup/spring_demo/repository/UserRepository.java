package com.techup.spring_demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techup.spring_demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
