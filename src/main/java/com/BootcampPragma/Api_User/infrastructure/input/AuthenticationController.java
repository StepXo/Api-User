package com.BootcampPragma.Api_User.infrastructure.input;

import com.BootcampPragma.Api_User.application.dto.AuthenticationRequest;
import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.handler.AuthenticationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationHandler service;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse token = service.login(request);
        return  ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(service.register(request,"ADMIN")); //debug line
        //return ResponseEntity.ok(service.register(request,"CLIENT"));
    }

}
