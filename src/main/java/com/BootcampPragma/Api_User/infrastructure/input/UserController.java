package com.BootcampPragma.Api_User.infrastructure.input;

import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.application.handler.AuthenticationHandler;
import com.BootcampPragma.Api_User.application.handler.UserHandler;
import com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {

    private final UserHandler userService;
    private final AuthenticationHandler authService;


    @GetMapping(InfraConstants.USER)
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request, String.valueOf(request.getRole())));
    }

}
