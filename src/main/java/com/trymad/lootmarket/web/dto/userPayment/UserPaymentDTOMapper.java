package com.trymad.lootmarket.web.dto.userPayment;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.UserPayment;

@Mapper(componentModel = "spring")
public interface UserPaymentDTOMapper {

    UserPaymentDTO toDto(UserPayment userPayment);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "paymentSystem", ignore = true)
    UserPayment toEntity(UserPaymentDTO userPaymentDTO);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "paymentSystem", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserPayment toEntity(UserPaymentCreateDTO userPaymentCreateDTO);

    List<UserPaymentDTO> toDto(List<UserPayment> userPayments);

}
