package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
  List<Users> findByVerified(boolean verified);

  List<Users> findByName(String title);
}
