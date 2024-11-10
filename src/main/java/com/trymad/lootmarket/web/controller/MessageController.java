package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.Message;
import com.trymad.lootmarket.service.message.MessageService;
import com.trymad.lootmarket.web.dto.mapper.MessageDtoMapper;
import com.trymad.lootmarket.web.dto.message.MessageDTO;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;
    private final MessageDtoMapper messageDtoMapper;

    @GetMapping
    public List<MessageDTO> getMessagesByParam(
            @RequestParam(required = false) UUID senderId,
            @RequestParam(required = false) UUID recipientId,
            @RequestParam(required = false) LocalDateTime timeFrom,
            @RequestParam(required = false) LocalDateTime timeTo) {
        List<Message> messages = messageService.get(senderId, recipientId, timeFrom, timeTo);
        return messageDtoMapper.toDto(messages);
    }

}
