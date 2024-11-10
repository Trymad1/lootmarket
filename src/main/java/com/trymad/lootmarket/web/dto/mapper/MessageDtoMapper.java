package com.trymad.lootmarket.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.trymad.lootmarket.model.Message;
import com.trymad.lootmarket.web.dto.message.MessageDTO;

@Mapper(componentModel = "spring")
public interface MessageDtoMapper {

    MessageDTO toDto(Message message);

    List<MessageDTO> toDto(List<Message> message);

    // default UUID map(User user) {
    //     return user.getId();
    // }

}
