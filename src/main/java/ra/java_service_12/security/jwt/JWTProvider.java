package ra.java_service_12.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTProvider {
    @Value("${jwt_secret}")
    private String jwt_secret;

    @Value("${jwt_expire}")
    private String jwt_expire;

    @Value("${jwt_refresh}")
    private String jwt_refresh;


    public String generateToken(String username){
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+jwt_expire))
                .signWith(SignatureAlgorithm.HS512, jwt_secret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwt_secret).parseClaimsJwt(token);
            return true;
        }catch (Exception e){
            log.error("Something was wrong");
        }
        return false;
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String refreshToken(String token,String username){
        if(validateToken(token) && getUsernameFromToken(token).equals(username)){
            Date now = new Date();
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + jwt_refresh))
                    .signWith(SignatureAlgorithm.HS512, jwt_secret)
                    .compact();
        }
        return null;
    }
}
