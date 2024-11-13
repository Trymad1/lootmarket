package com.trymad.lootmarket.web.dto.message;

import java.util.UUID;

public record MessageCreateDTO(UUID senderId, UUID recipientId, String messageText) {
    
}
