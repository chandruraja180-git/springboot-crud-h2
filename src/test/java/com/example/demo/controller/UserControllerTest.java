package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private final UserService userService = mock(UserService.class);
    private final UserController userController = new UserController(userService);

    @Test
    void testCreateUser() {
        User user = new User(1L, "Test", "test@mail.com");
        when(userService.createUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> response = userController.create(user);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test", response.getBody().getName());
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAllUsers())
                .thenReturn(List.of(new User(1L, "Test", "test@mail.com")));

        ResponseEntity<List<User>> response = userController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }
}
