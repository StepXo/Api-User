package com.BootcampPragma.Api_User.infrastructure.input;

import com.BootcampPragma.Api_User.application.dto.UserRequestDto;
import com.BootcampPragma.Api_User.application.dto.UserResponseDto;
import com.BootcampPragma.Api_User.application.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserHandler service;

    /*@PostMapping("/login")
    public ResponseEntity<UserResponseDto> authenticate(
            @RequestBody UserRequestDto request
    ) {
        return  ResponseEntity.ok(service.authenticate(request));
    }*/

    @PostMapping("/register")
    public ResponseEntity<UserRequestDto> register(
            @RequestBody UserRequestDto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto userResponseDto = service.getUserById(id);
        return ResponseEntity.ok(userResponseDto);
    }

}
