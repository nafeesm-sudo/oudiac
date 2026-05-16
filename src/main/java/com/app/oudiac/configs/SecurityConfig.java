package com.app.oudiac.configs;



import com.app.oudiac.models.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.Jwts;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecretKey secretKey() {
        MacAlgorithm algorithm = Jwts.SIG.HS256;
        SecretKey secretKey = algorithm.key().build();
        return secretKey;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/admin/oudiac/**").hasRole(Role.ADMIN.name())
//                        .requestMatchers("/api/users/oudiac/**").hasRole(Role.USER.name())
//                        .requestMatchers("/api/auth/**","/api/users/login","/api/admin/register").permitAll()// allow OTP APIs
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable()) // disable default login page
                .httpBasic(basic -> basic.disable()); // disable basic auth

        return http.build();
    }
}



//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//    http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//
//                    // 🔴 Admin only
//                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
//
//                    // 🟢 User only
//                    .requestMatchers("/api/user/**").hasRole("USER")
//
//                    // 🟡 Both ADMIN & USER
//                    .requestMatchers("/api/common/**").hasAnyRole("ADMIN", "USER")
//
//                    // 🔓 Public APIs (OTP / login)
//                    .requestMatchers("/api/auth/**").permitAll()
//
//                    // 🔒 Everything else needs authentication
//                    .anyRequest().authenticated()
//            )
//            .formLogin(form -> form.disable())
//            .httpBasic(basic -> basic.disable());
//
//    return http.build();
//}
