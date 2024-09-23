package com.BootcampPragma.Api_User.infrastructure.input;

import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.application.handler.AuthenticationHandler;
import com.BootcampPragma.Api_User.application.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {

    private final UserHandler userService;
    private final AuthenticationHandler authService;


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserByIdDocument(@PathVariable String id) {
        UserResponse userResponse = userService.getUserByIdDocument(id);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/register/aux")
    public ResponseEntity<AuthenticationResponse> registerAux(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request,"AUX_BODEGA"));
    }
    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request,"ADMIN"));
    }

}
