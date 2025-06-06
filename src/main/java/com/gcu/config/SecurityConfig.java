package com.gcu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/**").authenticated()
                .anyRequest().permitAll())
            .formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/users", true)
                .permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/").permitAll())

        .csrf(csrf -> csrf.disable());
        
        return http.build();
}
	 @Bean
	    public InMemoryUserDetailsManager userDetailsService() {
	        UserDetails user = User.builder()
	                .username("admin")
	                .passwordEncoder(passwordEncoder()::encode)
	                .password("password")
	                .roles("USER")
	                .build();
	        return new InMemoryUserDetailsManager(user);
	    }
}
