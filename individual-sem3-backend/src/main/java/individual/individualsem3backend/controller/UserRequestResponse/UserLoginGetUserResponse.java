package individual.individualsem3backend.controller.UserRequestResponse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginGetUserResponse {
    @NotNull
    private Integer id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    private String street;
    private Integer housenumber;
    private String zipcode;
    private String city;
    private String country;
    private String phonenumber;
}
