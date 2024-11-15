package com.trymad.lootmarket.service.userPayment;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.UserPayment;
import com.trymad.lootmarket.repository.userPayment.UserPaymentRepository;
import com.trymad.lootmarket.service.PaymentSystemService;
import com.trymad.lootmarket.service.UserService;
import com.trymad.lootmarket.web.dto.userPayment.UserPaymentCreateDTO;
import com.trymad.lootmarket.web.dto.userPayment.UserPaymentDTOMapper;
import com.trymad.lootmarket.web.dto.userPayment.UserPaymentUpdateDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class UserPaymentService {

    private final UserPaymentDTOMapper userPaymentDTOMapper;
    private final UserService userService;
    private final PaymentSystemService psService;
    private final UserPaymentRepository upRepository;

    @Transactional(readOnly = true)
    public List<UserPayment> getByUserId(UUID uuid) {
        if (!userService.existsById(uuid)) {
            throw userService.notFoundExceptionById(uuid);
        }

        return upRepository.findByUserId(uuid);
    }

    @Transactional(readOnly = true)
    public UserPayment getById(Long id) {
        return upRepository.findById(id).orElseThrow(
                () -> notFoundExceptionById(id));
    }

    public EntityNotFoundException notFoundExceptionById(Long id) {
        return new EntityNotFoundException("UserPayment with id " + id + " not found");
    }

    @Transactional
    public UserPayment create(UserPaymentCreateDTO userPaymentDTO, UUID userId) {
        final Long psId = userPaymentDTO.paymentId();

        if (!userService.existsById(userId)) {
            throw userService.notFoundExceptionById(userId);
        }

        if (!psService.existsById(psId)) {
            throw psService.notFoundExceptionById(psId);
        }

        final UserPayment up = userPaymentDTOMapper.toEntity(userPaymentDTO);
        up.setPaymentSystem(psService.getReference(psId));
        up.setUser(userService.getReference(userId));

        return upRepository.save(up);
    }

    @Transactional
    public UserPayment update(Long id, UserPaymentUpdateDTO upUpdateDTO) {
        final UserPayment userPayment = getById(id);

        userPayment.setDetails(upUpdateDTO.details());

        return upRepository.save(userPayment);
    }

    @Transactional
    public void delete(Long id) {
        upRepository.deleteById(id);
    }
}
