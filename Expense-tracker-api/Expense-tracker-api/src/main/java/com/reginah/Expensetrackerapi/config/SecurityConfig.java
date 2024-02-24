package com.reginah.Expensetrackerapi.config;

import com.reginah.Expensetrackerapi.service.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.security.SecureRandom;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService appUserDetailService;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsService appUserDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserDetailService = appUserDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(daoAuthenticationProvider())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/register").permitAll()
                                .requestMatchers("/users/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()).build();
    }

    private DaoAuthenticationProvider daoAuthenticationProvider () {
        var daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(this.appUserDetailService);
        daoProvider.setPasswordEncoder(this.passwordEncoder);
        return daoProvider;
    }
//FLOW OF
//    SpringSecurity -> DaoAuthothenProvider -> UserDetailService -> UserDetail -> userRepository -> User entity


}






//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }







//        @Bean
////    public UserDetailsService userDetailsService(PasswordEncoder bCryptPasswordEncoder) {
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("user")
////                .password(bCryptPasswordEncoder.encode("userPass"))
////                .roles("USER")
////                .build());
////        manager.createUser(User.withUsername("admin")
////                .password(bCryptPasswordEncoder.encode("adminPass"))
////                .roles("USER", "ADMIN")
////                .build());
////        return manager;
////    }

//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers("/login", "/register").permitAll()
//                                .requestMatchers("/api/v1/**")
//                                .authenticated()
//                )
//                .httpBasic(Customizer.withDefaults()).build();


//@Bean
//public UserDetailsService userDetailsService() {
//    UserDetails user =
//            User.withDefaultPasswordEncoder()
//                    .username("user")
//                    .password("password")
//                    .roles("USER")
//                    .build();
//
//    UserDetails admin =
//            User.withDefaultPasswordEncoder()
//                    .username("admin")
//                    .password("password")
//                    .roles("ADMIN", "USER")
//                    .build();
//
//    return new InMemoryUserDetailsManager(user, admin);
//}








