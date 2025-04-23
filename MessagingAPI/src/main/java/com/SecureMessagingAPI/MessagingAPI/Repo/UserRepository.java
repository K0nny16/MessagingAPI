package com.SecureMessagingAPI.MessagingAPI.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SecureMessagingAPI.MessagingAPI.Entity.UserEntity;
import java.util.UUID;
import java.util.Optional;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,UUID> {
    Optional<UserEntity> findByDisplayName(String displayName);
}