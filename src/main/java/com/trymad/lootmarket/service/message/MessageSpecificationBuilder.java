package com.trymad.lootmarket.service.message;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.trymad.lootmarket.model.Message;

@Component
class MessageSpecificationBuilder {

    public Specification<Message> buildSpecification(
            UUID sender, UUID recipient, LocalDateTime from, LocalDateTime to) {
        return Specification.where(senderSpec(sender))
                .and(recipitentSpec(recipient))
                .and(toSpec(to))
                .and(fromSpec(from));
    }

    private Specification<Message> senderSpec(UUID sender) {
        return (root, query, builder) -> sender == null ? null
                : builder.equal(root.get("senderId"), sender);
    }

    private Specification<Message> recipitentSpec(UUID recipient) {
        return (root, query, builder) -> recipient == null ? null
                : builder.equal(root.get("recipientId"), recipient);
    }

    private Specification<Message> toSpec(LocalDateTime to) {
        return (root, query, builder) -> to == null ? null
                : builder.lessThanOrEqualTo(root.get("sendDate"), to);
    }

    private Specification<Message> fromSpec(LocalDateTime from) {
        return (root, query, builder) -> from == null ? null
                : builder.greaterThanOrEqualTo(root.get("sendDate"), from);
    }

}
