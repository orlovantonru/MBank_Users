package com.minibank.mbank_users.service;

import com.minibank.mbank_users.model.User;
import com.minibank.mbank_users.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private UserService userService;
    AutoCloseable autoCloseable;
    User user;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
        user = new User(1L,"Ivan","Ivanov", "Ivanovich","ivv","ivv@mail.com","xxx");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testUpdateUser() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.saveUser(user)).isEqualTo("Success");
    }

    @Test
    void testDeleteUser() {
        mock(User.class);
        mock(UserRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(userRepository)
                .deleteById(any());
        assertThat(userService.deleteById(1L)).isEqualTo("Success");
    }

    @Test
    void testGetUser() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));

        assertThat(userService.findById(1L).getFirstName())
                .isEqualTo(user.getFirstName());
    }

    @Test
    void testGetAllUsers() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.findAll()).thenReturn(new ArrayList<User>(
                Collections.singleton(user)
        ));

        assertThat(userService.findAll().get(0).getFirstName()).
                isEqualTo(user.getFirstName());

    }
}