package com.BootcampPragma.Api_User.application.handler;

import com.BootcampPragma.Api_User.application.dto.AuthenticationResponse;
import com.BootcampPragma.Api_User.application.dto.UserRequest;
import com.BootcampPragma.Api_User.application.dto.UserResponse;
import com.BootcampPragma.Api_User.application.mapper.AuthenticationMapper;
import com.BootcampPragma.Api_User.application.mapper.UserHandlerMapper;
import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.model.Authentication;
import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.domain.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler {
    private  final UserServicePort userServicePort;
    private final UserHandlerMapper userHandlerMapper;

<<<<<<< Updated upstream


    public UserResponse getUserByIdDocument(String id) {
        User user = userServicePort.getUserByIdDocument(id);
=======
    public UserResponse getUserById(String id) {
        User user = userServicePort.getUserById(id);
>>>>>>> Stashed changes

        return userHandlerMapper.toUserResponseDto(user);
    }
}
