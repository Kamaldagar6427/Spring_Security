package com.api.security_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.security_api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserById(Long id);

	Optional<User> findByName(String name);

}
