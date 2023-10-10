package com.minibank.mbank_users.repository;

import com.minibank.mbank_users.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;


@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    User user;
    @BeforeEach
    void setUp() {
        user = new User(1L,"Ivan","Ivanov", "Ivanovich","ivv","ivv@mail.com","xxx");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        user = null;
        userRepository.deleteAll();
    }
    @Test

    public void findById() {
        User user = userRepository.findById(1L).get();
        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }

    @Test

    public void getListOfUserTest(){

        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);

    }

    @Test

    public void updateUserTest(){

        if (userRepository.findById(1L).isPresent()) {

            User user = userRepository.findById(1L).get();

            user.setEmail("ram@gmail.com");

            User userUpdated = userRepository.save(user);

            Assertions.assertThat(userUpdated.getEmail()).isEqualTo("ram@gmail.com");
        }
    }

    @Test

    public void deleteUserTest() {

        if (userRepository.findById(1L).isPresent()) {
            User user = userRepository.findById(1L).get();

            userRepository.delete(user);

            User user1 = null;

            Optional<User> optionalUser = userRepository.findByUserName("iw");

            if (optionalUser.isPresent()) {
                user1 = optionalUser.get();
            }

            Assertions.assertThat(user1).isNull();
        }
    }
}
