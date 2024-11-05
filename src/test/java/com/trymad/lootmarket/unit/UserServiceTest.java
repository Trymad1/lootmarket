package com.trymad.lootmarket.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.trymad.lootmarket.model.User;
import com.trymad.lootmarket.repository.user.UserRepository;
import com.trymad.lootmarket.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    public void createUser() {
        this.user = User.builder().id(UUID.fromString("98ec4822-d6c2-463f-9347-7fcbe360a7e1"))
                .name("Name")
                .mail("mockmail@gmail.com")
                .password("password")
                .placeService(true)
                .placeReview(true)
                .useService(true)
                .registrationDate(LocalDateTime.now())
                .lastEnter(LocalDateTime.now())
                .lastUpdate(LocalDateTime.now())
                .build();

    }

    @Test
    void shouldReturnUserWhenUserExists() {
        final UUID userId = user.getId();
        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        final User getUser = userService.get(userId);

        assertEquals(getUser, this.user);
    }

    @Test
    void shouldThrowExceptionWhenUserNotExists() {
        final UUID userId = user.getId();
        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(null));

        assertThrows(EntityNotFoundException.class, () -> {
            userService.get(userId);
        });
    }

    @Test
    void shouldReturnListOfUsers() {
        final List<User> userList = List.of(
                User.builder().id(UUID.randomUUID()).build(),
                User.builder().id(UUID.randomUUID()).build(),
                User.builder().id(UUID.randomUUID()).build());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> usersGet = userService.getAll();

        assertEquals(userList, usersGet);
    }

    @Test
    void shouldGenerateUUIDAndSetDatesWhenSaveNewUser() {
        user.setLastEnter(null);
        user.setRegistrationDate(null);
        user.setLastUpdate(null);
        user.setId(null);
        when(userRepository.save(user)).thenReturn(user);

        userService.save(user);

        assertThat(List.of(user.getLastEnter(), user.getRegistrationDate(), user.getLastUpdate(), user.getId()))
                .isNotNull();
        assertThat(List.of(user.getLastEnter(), user.getRegistrationDate(), user.getLastUpdate()))
                .containsOnly(user.getLastEnter());
    }

    @Test
    void shouldUpdateUserIfAlreadyExistsWhenUpdate() {
        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        final LocalDateTime updateOld = user.getLastUpdate();
        userService.update(user);

        assertThat(user.getLastUpdate().isAfter(updateOld));
    }

    @Test
    void shouldThrowExceptionIfUserNotExistsWhenUpdate() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(null));

        assertThrows(EntityNotFoundException.class, () -> {
            userService.update(user);
        });
    }

}
