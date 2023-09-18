package individual.individualsem3backend.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private Double price;
    private String description;
}
