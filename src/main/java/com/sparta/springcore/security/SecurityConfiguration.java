package com.sparta.springcore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> {
                            try {
                                request
                                        //images ,css 를 로그인 없이 허용
                                        .requestMatchers("/images/**", "/css/**").permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        //로그인 기능허용
                                        .formLogin()
                                        .loginPage("/user/login")
                                        .defaultSuccessUrl("/")
                                        .failureUrl("/user/login?error")
                                        .permitAll()
                                        .and()
                                        // 로그아웃 기능 허용
                                        .logout()
                                        .permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .httpBasic(withDefaults());
        return http.build();
    }

}












// image 폴더를 login 없이 허용
//                .antMatchers("/images/**").permitAll()
// css 폴더를 login 없이 허용
//                        .antMatchers("/css/**").permitAll()

