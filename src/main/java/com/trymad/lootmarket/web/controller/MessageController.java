package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.model.Message;
import com.trymad.lootmarket.service.message.MessageService;
import com.trymad.lootmarket.web.dto.message.MessageCreateDTO;
import com.trymad.lootmarket.web.dto.message.MessageDTO;
import com.trymad.lootmarket.web.dto.message.MessageDtoMapper;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("{id}")
    public MessageDTO getMessageById(@PathVariable Long id) {
        return messageDtoMapper.toDto(messageService.get(id));
    }

    @PostMapping
    public MessageDTO save(@RequestBody MessageCreateDTO messageCreateDTO) {
        return messageDtoMapper.toDto(messageService.save(messageCreateDTO));
    }

}
