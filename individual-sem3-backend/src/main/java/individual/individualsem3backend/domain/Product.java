package individual.individualsem3backend.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Integer id;
    @NotBlank
    private String name;
    private Double price;
    private String description;
    private String color;
}
