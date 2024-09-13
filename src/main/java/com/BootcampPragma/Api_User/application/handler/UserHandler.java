package com.BootcampPragma.Api_User.application.handler;

import com.BootcampPragma.Api_User.application.dto.UserRequestDto;
import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler {
    private  final UserServicePort userServicePort;
    private final PasswordEncoder passwordEncoder;



    public UserRequestDto register(UserRequestDto userDto) {
        User user = User.builder().name(userDto.getName())
                .lastName(userDto.getLastName())
                .id(userDto.getId())
                .email(userDto.getEmail())
                .idDocument(userDto.getIdDocument())
                .phoneNumber(userDto.getPhoneNumber())
                .birthDate(userDto.getBirthDate())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roleEnum(RoleEnum.USER).build();

        userServicePort.register(user);
        return userDto;
    }

}
