package swp391.SPS.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "picture")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class Picture {
    @Id
    @Column(name = "picture_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pictureId;

    @Column(name = "main")
    private String main;

    @Column(name = "front")
    private String front;

    @Column(name = "back")
    private String back;

    @Column(name = "side")
    private String site;

}
