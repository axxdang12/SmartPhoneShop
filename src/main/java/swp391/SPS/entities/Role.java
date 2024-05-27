package swp391.SPS.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class Role {
  @Id
  @Column(name = "role_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int roleId;

  @Column(name = "role_name")
  private String roleName;

  @ManyToMany(mappedBy = "roles")
  private List<User> users;
}
