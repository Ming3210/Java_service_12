package ra.java_service_12.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity (securedEnabled = true)
@Configuration
public class SpringSecurity {
    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(crsf-> crsf.disable())
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest

                        .requestMatchers("api/v1/user/**").hasAnyRole("ADMIN","USER")
                        .requestMatchers("api/v1/admin/**").hasRole("ADMIN")
                        .requestMatchers("api/v1/moderator/**").hasAnyRole("ADMIN","MODERATOR")
                        .anyRequest().permitAll())
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

}
