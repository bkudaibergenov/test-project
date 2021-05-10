package com.example.demo.repository;

import com.example.demo.entity.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    List<AnimalEntity> findByName(String name);

}
