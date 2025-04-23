package com.SecureMessagingAPI.MessagingAPI.Entity;
import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "users", schema = "public")
public class UserEntity {
    
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "createdAt", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "displayName", nullable = false, unique = true)
    private String displayName;

    @Column(name = "profilePictureURL", nullable = true)
    private String profilePictureURL;

    //Cascadar så ifall användaren tar bort UserEntity så åker Authentity med.
    //Detta beytder också att det är UserEntity som äger IDt.
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private AuthEntity authEntity;

    public UserEntity orElseThrow(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }
}
