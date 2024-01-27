package com.Bank.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bank.Models.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Boolean existsByEmail(String email);
}
