package ra.java_service_12.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JWTResponse {
    private String username;
    private Boolean isEnable;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
}
