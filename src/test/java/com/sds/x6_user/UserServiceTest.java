package com.sds.x6_user;

import com.sds.x6_user.exception.UserException;
import com.sds.x6_user.model.User;
import com.sds.x6_user.repository.UserRepository;
import com.sds.x6_user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User sampleUser;

    @BeforeEach
    void setup() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setLogin("login");
        sampleUser.setFirstName("First");
        sampleUser.setLastName("Last");
    }

    @Test
    void create_shouldThrowUserException_onRepoError() {
        when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(UserException.class, () -> userService.create(sampleUser));
    }

    @Test
    void getById_shouldThrowUserException_onRepoError() {
        when(userRepository.getById(1L)).thenThrow(new RuntimeException("DB error"));
        assertThrows(UserException.class, () -> userService.getById(1L));
    }

    @Test
    void getAllUsers_shouldThrowUserException_onRepoError() {
        when(userRepository.getAll()).thenThrow(new RuntimeException("DB error"));
        assertThrows(UserException.class, () -> userService.getAllUsers());
    }

    @Test
    void update_shouldThrowUserException_onRepoError() {
        when(userRepository.update(any(User.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(UserException.class, () -> userService.update(1L, sampleUser));
    }

    @Test
    void isAvailable_shouldThrowUserException_onRepoError() {
        when(userRepository.isUserAvailable(1L)).thenThrow(new RuntimeException("DB error"));
        assertThrows(UserException.class, () -> userService.isAvailable(1L));
    }
}
