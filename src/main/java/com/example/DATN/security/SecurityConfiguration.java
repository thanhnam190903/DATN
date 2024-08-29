package com.example.DATN.security;

import com.example.DATN.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select id ,email ,password ,sdt ,trangthai ,username ,fullname ,diachi  from users where id = ?");
        manager.setAuthoritiesByUsernameQuery("select id ,name from roles where id = ?");
        return manager;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests(
                req -> req
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/home/**").permitAll()
                        .requestMatchers("/quantri/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
        ).formLogin(
                login -> login.loginPage("/showloginpage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll().defaultSuccessUrl("/home")
        ).logout(
                logout -> logout.permitAll()
                        .logoutSuccessUrl("/home")
        ).exceptionHandling(
                exception->exception.accessDeniedPage("/showPage403")

        );
        httpSecurity.csrf(csrf->csrf.disable());
        return httpSecurity.build();
    }
}
