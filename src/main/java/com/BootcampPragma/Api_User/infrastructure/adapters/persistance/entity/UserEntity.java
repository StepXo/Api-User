package com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity;

import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants.ID_ROLE;
import static com.BootcampPragma.Api_User.infrastructure.Utils.InfraConstants.ROLE_USER;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = ROLE_USER)
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Name(value = ID_ROLE)
    @Column(nullable = false)
    private RoleEnum role;

    @Column(unique = true)
    private String idDocument;

    @Column(nullable = false)
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
