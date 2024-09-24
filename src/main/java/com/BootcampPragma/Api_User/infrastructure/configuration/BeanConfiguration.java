package com.BootcampPragma.Api_User.infrastructure.configuration;

import com.BootcampPragma.Api_User.domain.api.AuthenticationServicePort;
import com.BootcampPragma.Api_User.domain.api.UserServicePort;
import com.BootcampPragma.Api_User.domain.spi.AuthenticationRepositoryPort;
import com.BootcampPragma.Api_User.domain.spi.UserRepositoryPort;
import com.BootcampPragma.Api_User.domain.usecase.AuthenticationUseCase;
import com.BootcampPragma.Api_User.domain.usecase.UserUseCase;
import com.BootcampPragma.Api_User.infrastructure.adapters.JpaAdapter.UserJpaAdapter;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.mapper.UserMapper;
import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.repository.UserRepository;
import com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.AuthenticationService;
import com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.jwtconfiguration.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final JwtService jwtService;

    @Bean
    public UserRepositoryPort userRepositoryPort() {
        return new UserJpaAdapter(userRepository, userMapper,jwtService);
    }

    @Bean
    public AuthenticationRepositoryPort authenticationRepositoryPort(AuthenticationManager authenticationManager) {
        return new AuthenticationService(userRepository, jwtService,authenticationManager);
    }

    @Bean
    public AuthenticationServicePort authenticationServicePort(AuthenticationConfiguration config) throws Exception {
        AuthenticationManager authenticationManager = config.getAuthenticationManager();
        return new AuthenticationUseCase(userRepositoryPort(), authenticationRepositoryPort(authenticationManager));
    }
    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userRepositoryPort());
    }

}

