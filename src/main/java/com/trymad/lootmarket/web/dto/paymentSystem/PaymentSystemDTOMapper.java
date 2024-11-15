package com.trymad.lootmarket.web.dto.paymentSystem;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.PaymentSystem;

@Mapper(componentModel = "spring")
public interface PaymentSystemDTOMapper {

    PaymentSystemDTO toDto(PaymentSystem paymentSystem);

    List<PaymentSystemDTO> toDto(List<PaymentSystem> paymentSystems);

    PaymentSystem toEntity(PaymentSystemDTO paymentSystemDTO);

    @Mapping(target = "id", ignore = true)
    PaymentSystem toEntity(PaymentSystemCreateDTO paymentSystemCreateDTO);

}
