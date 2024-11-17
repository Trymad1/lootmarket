package com.trymad.lootmarket.service.message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trymad.lootmarket.model.Message;
import com.trymad.lootmarket.repository.message.MessageRepository;
import com.trymad.lootmarket.service.UserService;
import com.trymad.lootmarket.web.dto.message.MessageCreateDTO;
import com.trymad.lootmarket.web.dto.message.MessageDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageSpecificationBuilder specificationBuilder;
    private final UserService userService;
    private final MessageDtoMapper messageDtoMapper;

    @Transactional(readOnly = true)
    public List<Message> get(UUID sender, UUID recipient, LocalDateTime from, LocalDateTime to) {
        if (log.isDebugEnabled()) {
            final String logSender = sender == null ? "null" : sender.toString();
            final String logRecipient = recipient == null ? "null" : recipient.toString();
            final String logFrom = from == null ? "null" : from.toString();
            final String logTo = to == null ? "null" : to.toString();

            log.debug(
                    "Get messages, filters: \n" +
                            "sender = " + logSender + "\n" +
                            "recipient = " + logRecipient + "\n" +
                            "from = " + logFrom + "\n" +
                            "to = " + logTo + "\n");
        }

        final Specification<Message> specification = specificationBuilder.buildSpecification(
                sender, recipient, from, to);

        if (sender != null)
            userService.existsByIdOrThrow(sender);
        if (recipient != null)
            userService.existsByIdOrThrow(recipient);

        return messageRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Message get(Long id) {
        log.debug("Get message, id: {}", id);

        return messageRepository.findById(id).get();
    }

    @Transactional
    public Message save(MessageCreateDTO messageDto) {
        log.debug("Save message");

        final Message message = messageDtoMapper.toEntity(messageDto);

        message.setRecipient(userService.get(messageDto.recipientId()));
        message.setSender(userService.get(messageDto.senderId()));
        message.setSendDate(LocalDateTime.now());

        return messageRepository.save(message);
    }
}
