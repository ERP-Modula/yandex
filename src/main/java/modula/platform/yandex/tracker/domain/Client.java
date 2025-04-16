package modula.platform.yandex.tracker.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "modula_id", nullable = false, unique = true)
    private UUID modulaId;

    @Column(name = "org_id", nullable = false)
    private String orgId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;
}



