package swp391.SPS.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accessory")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
public class Accessory {
    @Id
    @Column(name = "accessory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accessoryId;

    @Column(name = "accessory_name")
    private String accessoryName;

    @Column(name = "accessory_description")
    private String accessoryDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", referencedColumnName = "brand_id")
    private Brand brand;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "picture_id", referencedColumnName = "picture_id")
    private Picture picture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;
}
