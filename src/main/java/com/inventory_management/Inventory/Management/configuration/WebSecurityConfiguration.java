package com.inventory_management.Inventory.Management.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfiguration{
        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


        @Autowired
        private JwtRequestFilter jwtRequestFilter;

        @Autowired
        private PasswordEncoder passwordEncoder;
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.cors();
            httpSecurity.csrf().disable()
                    .authorizeHttpRequests().antMatchers("/authenticate","/Register").permitAll()

                    .antMatchers(HttpHeaders.ALLOW).permitAll()

                    .anyRequest().authenticated()

                    .and()

                    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)

                    .and()

                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            ;

            httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }




        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
            return authenticationConfiguration.getAuthenticationManager();
        }
}
