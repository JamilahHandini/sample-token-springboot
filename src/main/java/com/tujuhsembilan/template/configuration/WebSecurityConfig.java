package com.tujuhsembilan.template.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tujuhsembilan.template.configuration.property.SecurityProp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final SecurityProp securityProperties;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // @formatter:off

    // Generic HTTP Configuration
    http
      .authorizeRequests()
        // Allow all to access actuator, change to authority base on real world production app!
        .antMatchers("/actuator/**").permitAll()
        // Explicitly allow all access to registration endpoint
        .antMatchers(HttpMethod.POST, "/auth/register/**").permitAll()
        // Explicitly allow all access to JWT-based authorization endpoint
        .antMatchers(HttpMethod.POST, "/jwt/authorization/basic").permitAll()
        // (TEMPORARY) Allow all access to graphql endpoint
//        .antMatchers("/graphql/**").permitAll()
        // Allow all access to the graphql playground
//        .antMatchers("/graphql-playground/**").permitAll()
        // Allow all access to files endpoint, this could be changed to some kind of authority only
        .antMatchers("/files/**").permitAll()
            .antMatchers("/register/**").permitAll()
              .antMatchers("/sign-in/**").permitAll()
              .antMatchers("/tags-management/**").permitAll()
            .antMatchers("/talent-management/**").permitAll()
            .antMatchers("/master-management/**").permitAll()
            .antMatchers("/talent-position/**").permitAll()
            .antMatchers("/client-management/**").permitAll()
            .antMatchers("/mail/**").permitAll()

        .antMatchers(HttpMethod.GET, "/test-data/**").hasAuthority("READ_TEST_DATA")
        .antMatchers(HttpMethod.POST, "/test-data/**").hasAuthority("CREATE_TEST_DATA")
        .antMatchers(HttpMethod.PUT, "/test-data/**").hasAuthority("UPDATE_TEST_DATA")
        
        .antMatchers(HttpMethod.POST, "/mail/**").hasAuthority("SEND_EMAIL")

        .anyRequest().authenticated()
        .and()
      .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .sessionFixation().changeSessionId()
        .sessionConcurrency(session ->
          session
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false)
        )
        .and()
      .csrf()
        .disable()
    ;

    // Configuration for HTTP Basic
    http
      .httpBasic(Customizer.withDefaults())
    ;

    // Configuration for OAuth 2.0 and OpenID Connect 1.0 Auth
    http
      .oauth2Login(Customizer.withDefaults())
    ;

    // Configuration for SAML 2.0 Auth
    http
      .saml2Login(Customizer.withDefaults())
      .saml2Logout(Customizer.withDefaults())
    ;

    // @formatter:on
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}