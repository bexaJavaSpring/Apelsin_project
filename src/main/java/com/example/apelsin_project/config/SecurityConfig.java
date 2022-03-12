package com.example.apelsin_project.config;

import com.example.apelsin_project.entity.enums.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true,prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("bekhruz").password(passwordEncoder().encode("123")).authorities(Permission.CREATE.name()).
                and().withUser("user").password(passwordEncoder().encode("123")).authorities(Permission.READ_ALL.name()).
                and().withUser("manager").password(passwordEncoder().encode("123")).authorities(Permission.READ_ONE.name());

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                authorizeRequests().
                anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
