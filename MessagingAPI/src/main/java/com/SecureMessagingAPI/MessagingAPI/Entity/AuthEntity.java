package com.SecureMessagingAPI.MessagingAPI.Entity;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "auth",schema = "auth")
public class AuthEntity {
    
    @Id
    private UUID id;

    @Column(name ="email", unique = true, nullable = false)
    private String email;

    @Column(name = "passwordHash",nullable = false)
    private String passwordHash;

    @Column(name = "encryptedPrivateKey",nullable = false)
    @Lob
    private byte[] encryptedPrivateKey;

    @Column(name = "publicKey",nullable = false)
    @Lob
    private byte[] publicKey;
    

    //Foreign key koppling till users tabellen mellan id.
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private UserEntity user;
}
