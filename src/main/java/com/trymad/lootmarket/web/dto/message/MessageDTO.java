package com.trymad.lootmarket.web.dto.message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDTO(UUID senderId, UUID recipientId, LocalDateTime sendDate, String messageText) {

}
