package com.trymad.lootmarket.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trymad.lootmarket.service.security.AuthenticationService;
import com.trymad.lootmarket.web.dto.user.JwtResponse;
import com.trymad.lootmarket.web.dto.user.LoginDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginDTO loginDTO) {
        return new JwtResponse(authenticationService.createJwtToken(loginDTO));
    }

}
