package com.SecureMessagingAPI.MessagingAPI.Repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SecureMessagingAPI.MessagingAPI.Entity.MessageEntity;

@Repository
public interface MessagesRepository extends JpaRepository<Long,MessageEntity>{
    
}
