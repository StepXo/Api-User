package com.BootcampPragma.Api_User.application.handler;

import com.BootcampPragma.Api_User.application.dto.AuthenticationRequest;
import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.mapper.AuthenticationMapper;
import com.BootcampPragma.Api_User.domain.api.AuthenticationServicePort;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationHandler {
    private  final AuthenticationServicePort authenticationServicePort;
    private final AuthenticationMapper authenticationMapper;



    public AuthenticationResponse login(AuthenticationRequest authenticationDto) {

        Authentication authentication = Authentication.builder()
                .email(authenticationDto.getEmail())
                .password(authenticationDto.getPassword()).build();
        Authentication response = authenticationServicePort.login(authentication);
        return authenticationMapper.toAuthenticationResponse(response);
    }

}
