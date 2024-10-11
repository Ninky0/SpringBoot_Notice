package org.example.springboot_notice.config;

import org.example.springboot_notice.config.security.CustomAuthenticationFailureHandler;
import org.example.springboot_notice.config.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web)->web.ignoring()
                .requestMatchers(
                        "/static/**","/css/**", "/js/**"
                );
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            CustomAuthenticationSuccessHandler successHandler,
            CustomAuthenticationFailureHandler failureHandler
    ) throws Exception {

        http
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        new AntPathRequestMatcher("/users/register"),
                                        new AntPathRequestMatcher("/users/login"),
                                        new AntPathRequestMatcher("/users")
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(
                        form -> form
                                .loginPage("/users")
                                .loginProcessingUrl("/users/login")   //우리가 만든 /login 이 아니라, UserDetailService가 동작함
                                //.defaultSuccessUrl("/articles")
                                .successHandler(successHandler)
                                .failureHandler(failureHandler)
                )
                .logout(
                        logout -> logout
                                .logoutUrl("/users/logout")
                                .logoutSuccessUrl("/users")
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
