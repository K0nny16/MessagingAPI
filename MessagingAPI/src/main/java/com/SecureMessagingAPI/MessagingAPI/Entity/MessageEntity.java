package com.SecureMessagingAPI.MessagingAPI.Entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Messages", schema = "public")
public class MessageEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Skapar en foreign key mellan sender och ett id i UserEntity
    @ManyToOne
    @JoinColumn(name = "sender")
    private UserEntity userEntity;

    //Samma som ovan bara recipient istället för sender.
    @ManyToOne
    @JoinColumn(name = "recipient")
    private UserEntity userEntity2;

    @Column(name = "createdAt",nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "encryptedMessage", nullable = false)
    @Lob
    private byte[] encryptedMessage;

    @Column(name = "encryptedAesKey",nullable = false)
    @Lob
    private byte[] encryptedAesKey;

    @Column(name = "iv",nullable = false)
    private byte[] iv;
}
