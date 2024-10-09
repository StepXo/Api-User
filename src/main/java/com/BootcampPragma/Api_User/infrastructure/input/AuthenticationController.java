package com.BootcampPragma.Api_User.infrastructure.input;

import com.BootcampPragma.Api_User.application.dto.AuthenticationRequest;
import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.handler.AuthenticationHandler;
import com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(InfraConstants.AUTH_PATH)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationHandler service;


    @PostMapping(InfraConstants.LOGIN_PATH)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse token = service.login(request);
        return  ResponseEntity.ok(token);
    }

    @PostMapping(InfraConstants.REGISTER_PATH)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserRequest request
    ) {
        return ResponseEntity.ok(service.register(request, InfraConstants.ROLE_ADMIN)); //For debugging proposes
        //return ResponseEntity.ok(service.register(request,InfraConstants.ROLE_USER));
    }

}
