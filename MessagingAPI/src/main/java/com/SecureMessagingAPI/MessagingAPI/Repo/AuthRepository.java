package com.SecureMessagingAPI.MessagingAPI.Repo;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SecureMessagingAPI.MessagingAPI.Entity.AuthEntity;
import java.util.Optional;


@Repository
public interface AuthRepository extends JpaRepository<AuthEntity,UUID>{
    Optional<AuthEntity> findByEmail(String email);
}