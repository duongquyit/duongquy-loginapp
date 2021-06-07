package com.example.javaapp;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.javaapp.Entity.User;
import com.example.javaapp.Repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestReposity {
    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("abc@gmail.com");
        user.setPassword("password");
        user.setFirstName("Duong");
        user.setLastName("Quy");

        User saveUser = repo.save(user);
        User existUser = entityManager.find(User.class, saveUser.getId());
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    
}
