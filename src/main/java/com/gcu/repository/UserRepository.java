package com.gcu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
	
}
