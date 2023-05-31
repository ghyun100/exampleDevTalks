package com.teamproject.devTalks.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.teamproject.devTalks.filter.JwtAuthenticationFilter;

class FailedAuthenticationEntiryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"code\": \"AF\", \"message\": \"Authentication Failed\"}");
    }

}

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/user/sign-up", "/user/sign-in", "/user/{userNumber}","/user/find-email","/user/find-password").permitAll()
                .antMatchers("/admin/sign-up","/admin/sign-in").permitAll()
                .antMatchers("/web-socket").permitAll()
                .antMatchers(HttpMethod.PATCH, "/user/update").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/board/**", "/notice/**","/recommendation").permitAll()
                .antMatchers(HttpMethod.POST, "/board/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/board/**","/recommendation").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/board/**","/user/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/notice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/notice/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "notice/**", "qna/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntiryPoint());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();

    }
}
