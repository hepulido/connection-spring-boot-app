package com.hectorPulido.connectionApp;

import com.hectorPulido.connectionApp.user.User;
import com.hectorPulido.connectionApp.user.UserRepository;
import com.hectorPulido.connectionApp.user.UserService;
import com.hectorPulido.connectionApp.user.UserUpdateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> foundUser = userRepository.findById(1L);
        assertEquals(user.getId(), foundUser.orElseThrow().getId());
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userRepository.save(user);
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }
}
