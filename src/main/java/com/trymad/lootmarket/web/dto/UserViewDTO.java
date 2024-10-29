package com.trymad.lootmarket.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserViewDTO {

    private String id;
    private String name;
    private String mail;
    private LocalDateTime registationDate;
    private LocalDateTime lastEnter;
    private LocalDateTime lastUpdate;

}
