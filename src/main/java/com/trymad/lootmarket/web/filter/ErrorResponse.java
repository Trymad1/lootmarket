package com.trymad.lootmarket.web.filter;

import java.time.Instant;

public record ErrorResponse(

    int status,
    String message,
    String path,
    String method,
    Instant timestamp

) { }