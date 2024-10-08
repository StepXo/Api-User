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
<<<<<<< Updated upstream
    public ResponseEntity<UserResponseDto> getUserByIdDocument(@PathVariable String id) {
        UserResponseDto userResponseDto = service.getUserByIdDocument(id);
        return ResponseEntity.ok(userResponseDto);
=======
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/register/aux")
    public ResponseEntity<AuthenticationResponse> registerAux(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request,"WAREHOUSE_AUX"));
    }
    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request,"ADMIN"));
>>>>>>> Stashed changes
    }

}
