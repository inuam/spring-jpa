package com.au.spring_jpa.repositories;

import com.au.spring_jpa.entities.AdminUser;
import com.au.spring_jpa.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testPersistAndQueryAdminUser() {
        AdminUser admin = new AdminUser();
        admin.setUsername("alice");
        admin.setPassword("secret");
        admin.setPermissions("ALL");
        userRepository.save(admin);

        List<UserEntity> users = userRepository.findAll();
        assertThat(users.get(0)).isInstanceOf(AdminUser.class);
    }

//    @Test
//    void testPersistAndQueryCustomerUser() {
//        CustomerUser customer = new CustomerUser();
//        customer.setUsername("bob");
//        customer.setPassword("
}