package com.au.spring_jpa.repositories;


import com.au.spring_jpa.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Query by username (works across all subtypes)
    List<UserEntity> findByUsername(String username);

    // Polymorphic JPQL: fetch only admins
    @Query("SELECT u FROM UserEntity u WHERE TYPE(u) = AdminUser")
    List<UserEntity> findAllAdmins();

    // Polymorphic JPQL: fetch only customers
    @Query("SELECT u FROM UserEntity u WHERE TYPE(u) = CustomerUser")
    List<UserEntity> findAllCustomers();
}
