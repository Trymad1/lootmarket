package com.trymad.lootmarket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.Deal;
import com.trymad.lootmarket.model.DealStatus;
import com.trymad.lootmarket.model.DealStatusEntity;
import com.trymad.lootmarket.repository.deal.DealRepository;
import com.trymad.lootmarket.repository.deal.DealStatusRepository;
import com.trymad.lootmarket.web.dto.deal.DealCreateDTO;
import com.trymad.lootmarket.web.dto.deal.DealDTOMapper;
import com.trymad.lootmarket.web.dto.deal.DealUpdateDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class DealService {

    private final UserService userService;
    private final PaymentSystemService paymentSystemService;

    private final DealRepository dealRepository;
    private final DealStatusRepository dealStatusRepository;

    private final DealDTOMapper dealDTOMapper;

    @Transactional(readOnly = true)
    public List<Deal> getAll() {
        return dealRepository.fetchFindAll();
    }

    @Transactional(readOnly = true)
    public Deal getById(Long id) {
        return dealRepository.fetchFindByid(id).orElseThrow(() -> notFoundExceptionById(id));
    }

    @Transactional(readOnly = true)
    private DealStatusEntity getDealStatus(DealStatus dealStatus) {
        return dealStatusRepository.findByName(dealStatus).orElseThrow(
                () -> new EntityNotFoundException("Dealstatus " + dealStatus + " doesn`t exists"));
    }

    @Transactional(readOnly = true)
    public List<Deal> getByServiceId(Long id) {
        return dealRepository.fetchFindByServiceId(id);
    }

    @Transactional
    public Deal create(DealCreateDTO createDTO) {
        final Deal deal = dealDTOMapper.toEntity(createDTO);
        deal.setDealStart(LocalDateTime.now());
        deal.setBuyer(userService.get(createDTO.buyerId()));
        deal.setPaymentSystem(paymentSystemService.get(createDTO.paymentSystemId()));
        deal.setDealStatus(this.getDealStatus(createDTO.dealStatus()));

        return dealRepository.save(deal);
    }

    @Transactional
    public Deal update(DealUpdateDTO dealUpdateDTO, Long id) {
        final Deal deal = this.getById(id);
        deal.setDealStatus(this.getDealStatus(dealUpdateDTO.dealStatus()));
        deal.setBuyedQuantity(dealUpdateDTO.buyedQuantity());
        deal.setDealEnd(dealUpdateDTO.dealEnd());

        return dealRepository.save(deal);
    }

    @Transactional
    public void delete(Long id) {
        dealRepository.deleteById(id);
    }

    @Transactional
    public List<Deal> getByDate(LocalDateTime from, LocalDateTime to) {
        return dealRepository.fetchFindByDate(from, to);
    }

    public boolean exists(Long id) {
        return dealRepository.existsById(id);
    }

    public EntityNotFoundException notFoundExceptionById(Long id) {
        return new EntityNotFoundException("Deal with id " + id + " not found");
    }
}
