package com.trymad.lootmarket.service.message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.trymad.lootmarket.model.Message;
import com.trymad.lootmarket.repository.message.MessageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageSpecificationBuilder specificationBuilder;

    public List<Message> get(UUID sender, UUID recipient, LocalDateTime from, LocalDateTime to) {
        final Specification<Message> specification = specificationBuilder.buildSpecification(
                sender, recipient, from, to);

        return messageRepository.findAll(specification);
    }

}
