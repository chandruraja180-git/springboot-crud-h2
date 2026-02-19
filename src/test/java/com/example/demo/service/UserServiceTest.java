package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "Test", "test@mail.com");
        when(userRepository.save(user)).thenReturn(new User(1L, "Test", "test@mail.com"));

        User saved = userService.createUser(user);

        assertNotNull(saved);
        assertEquals("Test", saved.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = List.of(new User(1L, "Test", "test@mail.com"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        User user = new User(1L, "Test", "test@mail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals("Test", result.getName());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}
