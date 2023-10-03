package individual.individualsem3backend.controller.OrderRequestRespone;

import individual.individualsem3backend.domain.Product;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private Integer userId;
    private List<Product> products;
    private boolean bundle_or_not;
    private Date date_of_purchase;
}