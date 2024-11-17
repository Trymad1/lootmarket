package com.trymad.lootmarket.service.message;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.trymad.lootmarket.model.Message;

import jakarta.persistence.criteria.JoinType;

@Component
class MessageSpecificationBuilder {

    public Specification<Message> buildSpecification(
            UUID sender, UUID recipient, LocalDateTime from, LocalDateTime to) {
        return Specification.where(fetchQuery())
                .and(senderSpec(sender))
                .and(recipitentSpec(recipient))
                .and(toSpec(to))
                .and(fromSpec(from));
    }

    private Specification<Message> fetchQuery() {
        return (root, query, builder) -> {
            query.distinct(true);

            root.fetch("sender", JoinType.LEFT);
            root.fetch("recipient", JoinType.LEFT);

            return null;
        };
    }

    private Specification<Message> senderSpec(UUID sender) {
        return (root, query, builder) -> sender == null ? null
                : builder.equal(root.get("sender").get("id"), sender);
    }

    private Specification<Message> recipitentSpec(UUID recipient) {
        return (root, query, builder) -> recipient == null ? null
                : builder.equal(root.get("recipient").get("id"), recipient);
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
