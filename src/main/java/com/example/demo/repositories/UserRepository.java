package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    List<UserEntity> findByEmail(String email);   
    List<UserEntity> findByUsername(String username); 
} 
