package individual.individualsem3backend.controller.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequest {
    @NotBlank
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank
    private String password;

}
