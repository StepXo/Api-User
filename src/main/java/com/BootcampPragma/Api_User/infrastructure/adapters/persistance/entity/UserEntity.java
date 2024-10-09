package com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity;

import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Column(unique = true)
    private String idDocument;

    private String phoneNumber;

    private String birthDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(InfraConstants.ROLE + role.name()));
    }

    @Override
    public String getUsername() {
        return String.valueOf(id);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
