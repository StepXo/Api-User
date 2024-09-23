package com.BootcampPragma.Api_User.application.handler;

import com.BootcampPragma.Api_User.application.dto.AuthenticationRequest;
import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.mapper.AuthenticationMapper;
import com.BootcampPragma.Api_User.domain.api.AuthenticationServicePort;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;
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
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse register(UserRequest userDto,String role) {


        User user = buildUser(userDto,role);


        return authenticationMapper.toAuthenticationResponse(authenticationServicePort.register(user));
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationDto) {

        Authentication authentication = Authentication.builder()
                .email(authenticationDto.getEmail())
                .password(authenticationDto.getPassword()).build();
        Authentication response = authenticationServicePort.login(authentication);
        return authenticationMapper.toAuthenticationResponse(response);
    }

    private User buildUser(UserRequest user, String role) {
         return User.builder().name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .idDocument(user.getIdDocument())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .password(passwordEncoder.encode(user.getPassword()))
                .roleEnum(RoleEnum.valueOf(role)).build();
    }

}
