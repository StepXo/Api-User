package com.BootcampPragma.Api_User.application.dto;

import com.BootcampPragma.Api_User.domain.model.RoleEnum;
import lombok.*;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private long id;
    private String name;
    private String lastName;
    private String idDocument;
    private String phoneNumber;
    private String birthDate;
    private String email;
    private String password;
    private RoleEnum role;
}
