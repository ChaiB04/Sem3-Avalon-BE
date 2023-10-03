package individual.individualsem3backend.domain;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String street;
    private Integer housenumber;
    private String zipcode;
    private String city;
    private String country;
    private String phonenumber;

}