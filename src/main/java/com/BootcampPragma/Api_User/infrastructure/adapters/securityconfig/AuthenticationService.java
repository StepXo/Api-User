package com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig;

import com.BootcampPragma.Api_User.application.dto.UserRequestDto;
import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.repository.UserRepository;
import com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.jwtconfiguration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    /*public UserResponseDto authenticate(UserRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );
        var user = repository.findByName(request.getName()).orElseThrow();

        var jwtToken = jwtService.generate(user);
        return UserResponseDto.builder()
                .token(jwtToken)
                .build();
    }*/

    /*public UserResponseDto register(RegisterRequest registerRequest) {
        UserEntity user = UserEntity.builder().name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roleEnum(RoleEnum.USER).build();

        repository.save(user);

        return UserResponseDto.builder().token(jwtService.getToken(user)).build();
    }*/
}
