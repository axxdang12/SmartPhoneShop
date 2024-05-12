package swp391.SPS.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;
}
