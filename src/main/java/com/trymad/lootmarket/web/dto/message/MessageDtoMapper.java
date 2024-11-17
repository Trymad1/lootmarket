package com.trymad.lootmarket.web.dto.message;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.trymad.lootmarket.model.Message;
import com.trymad.lootmarket.model.User;

@Mapper(componentModel = "spring")
public interface MessageDtoMapper {

    @Mapping(target = "senderId", source = "sender")
    @Mapping(target = "recipientId", source = "recipient")
    MessageDTO toDto(Message message);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sendDate", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "recipient", ignore = true)
    Message toEntity(MessageCreateDTO messageCreateDTO);

    List<MessageDTO> toDto(List<Message> message);

    default UUID map(User user) {
        return user.getId();
    }

}
