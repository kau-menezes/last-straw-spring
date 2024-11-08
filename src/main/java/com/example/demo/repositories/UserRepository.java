package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    List<UserEntity> findByEmail(String email);   
    Optional<UserEntity> findOneByEmail(String email);   
    List<UserEntity> findByUsername(String username); 
    Optional<UserEntity> findOneByUsername(String username); 
} 
