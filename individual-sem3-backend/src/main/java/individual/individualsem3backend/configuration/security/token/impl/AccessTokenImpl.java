package individual.individualsem3backend.configuration.security.token.impl;

import individual.individualsem3backend.configuration.security.token.AccessToken;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Integer userId;
    private final String roles;

    public AccessTokenImpl(String subject, Integer userId, String roles) {
        this.subject = subject;
        this.userId = userId;
        this.roles = roles != null ? roles : "DEFAULT";
    }

}
