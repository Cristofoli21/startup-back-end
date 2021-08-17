package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Devices;

public interface UsersRepository extends JpaRepository<Users, Long> {
  List<Users> findByPrice(String price);

  List<Users> findByName(String title);
}
