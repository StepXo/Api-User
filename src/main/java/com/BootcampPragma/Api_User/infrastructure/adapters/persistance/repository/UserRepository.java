package com.BootcampPragma.Api_User.infrastructure.adapters.persistance.repository;

import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdDocument(String id);
    Optional<UserEntity> findByEmail(String email);

}
