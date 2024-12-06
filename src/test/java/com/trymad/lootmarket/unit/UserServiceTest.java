package com.trymad.lootmarket.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        this.user = new User();
        this.user.setId(UUID.fromString("98ec4822-d6c2-463f-9347-7fcbe360a7e1"));
        this.user.setName("Name");
        this.user.setMail("mockmail@gmail.com");
        this.user.setPassword("password");
        this.user.setRegistrationDate(LocalDateTime.now());
        this.user.setLastEnter(LocalDateTime.now());
        this.user.setLastUpdate(LocalDateTime.now());

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
        final User user1 = new User();
        user1.setId(UUID.randomUUID());
        final User user2 = new User();
        user2.setId(UUID.randomUUID());
        final User user3 = new User();
        user3.setId(UUID.randomUUID());
        final List<User> userList = new ArrayList<>();
        userList.add(user3);
        userList.add(user2);
        userList.add(user1);

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
