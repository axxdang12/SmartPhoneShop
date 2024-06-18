package swp391.SPS.entities;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "orderItem")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class OrderItem {
    @Id
    @Column(name = "orderItem_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total")
    private int total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phone_id", referencedColumnName = "phone_id")
    private Phone phone;

}
