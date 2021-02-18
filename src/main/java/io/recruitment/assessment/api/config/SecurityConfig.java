package io.recruitment.assessment.api.config;

import io.recruitment.assessment.api.models.Role;
import io.recruitment.assessment.api.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/products").hasAuthority(Role.ADMIN.name())
                .mvcMatchers(HttpMethod.DELETE, "/products").hasAuthority(Role.ADMIN.name())
                .mvcMatchers(HttpMethod.PUT, "/products").hasAuthority(Role.ADMIN.name())
                .mvcMatchers(HttpMethod.PATCH, "/products").hasAuthority(Role.ADMIN.name())
                .mvcMatchers(HttpMethod.GET, "/products").hasAnyAuthority(Role.ADMIN.name(), Role.CUSTOMER.name())
                .mvcMatchers("/cart").hasAnyAuthority(Role.ADMIN.name(), Role.CUSTOMER.name())
                .and().httpBasic()

                .and().authorizeRequests().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
