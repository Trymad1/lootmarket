package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.Category;
import com.trymad.lootmarket.model.User;
import com.trymad.lootmarket.model.UserAd;
import com.trymad.lootmarket.repository.userAd.UserAdRepository;
import com.trymad.lootmarket.web.dto.userAd.UserAdCreateDTO;
import com.trymad.lootmarket.web.dto.userAd.UserAdDTOMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Component
public class UserAdService {

    private final UserAdRepository userAdRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final UserAdDTOMapper userAdDTOMapper;

    @Transactional(readOnly = true)
    public List<UserAd> getAll() {
        return userAdRepository.findAll();
    }

    @Transactional(readOnly = true)
    public UserAd getById(Long id) {
        return userAdRepository.fetchFindById(id).orElseThrow(
                () -> notFoundExceptionById(id));
    }

    @Transactional
    public UserAd create(UserAdCreateDTO createDto) {
        final UserAd userAd = userAdDTOMapper.toEntity(createDto);
        final Category category = categoryService.get(createDto.categoryId());
        final User user = userService.get(createDto.authorId());
        final LocalDateTime now = LocalDateTime.now();

        userAd.setAuthor(user);
        userAd.setCategory(category);
        userAd.setCreateDate(now);
        userAd.setUpdateDate(now);

        return userAdRepository.save(userAd);
    }

    @Transactional
    public UserAd update(UserAd userAd) {
        final UserAd userAdFromDb = this.getById(userAd.getId());
        final LocalDateTime now = LocalDateTime.now();

        userAd.setAuthor(userAdFromDb.getAuthor());
        userAd.setCategory(userAdFromDb.getCategory());
        userAd.setUpdateDate(now);

        return userAdRepository.save(userAd);

    }

    @Transactional
    public void deleteById(Long id) {
        userAdRepository.deleteById(id);
    }

    private EntityNotFoundException notFoundExceptionById(Long id) {
        return new EntityNotFoundException("UserAd with id " + id + " not found");
    }

}
