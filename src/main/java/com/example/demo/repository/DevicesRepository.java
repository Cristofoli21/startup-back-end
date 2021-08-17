package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Devices;

public interface DevicesRepository extends JpaRepository<Devices, Long> {
  List<Devices> findByPrice(String price);

  List<Devices> findByName(String title);
}
