package com.trymad.lootmarket.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.PaymentSystem;
import com.trymad.lootmarket.repository.paymentSystem.PaymentSystemRepository;
import com.trymad.lootmarket.web.dto.paymentSystem.PaymentSystemCreateDTO;
import com.trymad.lootmarket.web.dto.paymentSystem.PaymentSystemDTOMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class PaymentSystemService {

    private final PaymentSystemRepository psRepository;
    private final PaymentSystemDTOMapper psDTOMapper;

    @Transactional(readOnly = true)
    public List<PaymentSystem> getAll() {
        return psRepository.findAll();
    }

    public boolean existsById(Long id) {
        return psRepository.existsById(id);
    }

    public PaymentSystem getReference(Long id) {
        return psRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public PaymentSystem get(Long id) {
        return psRepository.findById(id).orElseThrow(
                () -> notFoundExceptionById(id));
    }

    public EntityNotFoundException notFoundExceptionById(Long id) {
        return new EntityNotFoundException("Payment system with id " + id + " not found");
    }

    @Transactional
    public PaymentSystem create(PaymentSystemCreateDTO psCreateDTO) {
        final PaymentSystem ps = psDTOMapper.toEntity(psCreateDTO);
        return psRepository.save(ps);
    }

    @Transactional
    public PaymentSystem update(PaymentSystem ps) {
        if (!psRepository.existsById(ps.getId())) {
            throw notFoundExceptionById(ps.getId());
        }

        return psRepository.save(ps);
    }

    @Transactional
    public void delete(Long id) {
        psRepository.deleteById(id);
    }
}
