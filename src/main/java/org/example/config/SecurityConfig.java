package org.example.config;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/products", "/login", "/register", "/error", "/css/**", "/js/**", "/images/**", "/h2-console/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
        );

        http.formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutSuccessUrl("/products")
                .permitAll()
        );

        // Keep CSRF protection enabled for normal application requests.
        // The local H2 console is excluded because it is a development-only tool.
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> {
            User user = repo.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(normalizeStoredPassword(user.getPassword()))
                    .roles(user.getRole())
                    .build();
        };
    }

    /**
     * Older local databases may still contain the original plaintext demo passwords.
     * Prefixing those values with {noop} preserves local compatibility while all newly
     * saved passwords are encoded by UserService.
     */
    private String normalizeStoredPassword(String storedPassword) {
        if (storedPassword == null || storedPassword.isBlank()) {
            return "{noop}";
        }
        if (storedPassword.startsWith("{")) {
            return storedPassword;
        }
        return "{noop}" + storedPassword;
    }
}
