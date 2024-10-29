package com.trymad.lootmarket.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserCreateDTO {

    private String name;
    private String mail;
    private String password;

}
