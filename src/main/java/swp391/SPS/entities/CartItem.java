package swp391.SPS.entities;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private int productId;
    private String picture;
    private String name;
    private double price;
    private int quantity=1;
}
