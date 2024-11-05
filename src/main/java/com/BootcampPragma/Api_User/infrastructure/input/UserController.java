package com.BootcampPragma.Api_User.infrastructure.input;

import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.SetRoleRequest;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.application.handler.AuthenticationHandler;
import com.BootcampPragma.Api_User.application.handler.UserHandler;
import com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class UserController {

    private final UserHandler userService;
    private final AuthenticationHandler authService;

    @PreAuthorize(InfraConstants.HAS_ROLE_ADMIN)
    @GetMapping(InfraConstants.USER)
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    @PreAuthorize(InfraConstants.HAS_ROLE_ADMIN)
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request, String.valueOf(request.getRole())));
    }

    @PostMapping(InfraConstants.ROLE_PATH)
    @PreAuthorize(InfraConstants.HAS_ROLE_ADMIN)
    public ResponseEntity<String> setRole(
            @RequestBody SetRoleRequest setRoleRequest
            ) {
        return ResponseEntity.ok(userService.setRole(setRoleRequest.getId(),setRoleRequest.getRole()));
    }

}
