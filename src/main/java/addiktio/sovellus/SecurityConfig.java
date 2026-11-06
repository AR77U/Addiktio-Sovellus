package addiktio.sovellus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
@EnableWebSecurity 


public class SecurityConfig {

    @Bean 
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/","/leaderboard/**").permitAll()
            .anyRequest().authenticated()
        )
            .formLogin(formlogin -> formlogin
                .defaultSuccessUrl("/profiili", true)
            .permitAll()
        )
        .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")
        
    )
    .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())
);
        return http.build();
    }
    @Bean 
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
