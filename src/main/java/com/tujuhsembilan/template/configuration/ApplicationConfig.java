package com.tujuhsembilan.template.configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Random;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import com.tujuhsembilan.template.configuration.property.SecurityProp;

@Configuration
@EnableScheduling
public class ApplicationConfig {
  private final Random r;

  private final RSAPublicKey rsaPub;
  private final RSAPrivateKey rsaPrv;

  private final SecurityProp securityProp;


  @Autowired
  public ApplicationConfig(SecurityProp securityProp)
      throws NoSuchAlgorithmException {
    this.r = new Random();

    KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
    rsaGen.initialize(2048);

    KeyPair rsaKey = rsaGen.genKeyPair();

    this.rsaPub = (RSAPublicKey) rsaKey.getPublic();
    this.rsaPrv = (RSAPrivateKey) rsaKey.getPrivate();

    // ---

    this.securityProp = securityProp;

    // ---
  }


  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(rsaPub).build();
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    // @formatter:off
    return new NimbusJwtEncoder(
      new ImmutableJWKSet<>(
        new JWKSet(
          new RSAKey
            .Builder(rsaPub)
              .privateKey(rsaPrv)
            .build()
        )
      )
    );
    // @formatter:on
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(securityProp.getStrength());
  }


  @Bean
  public Random random() {
    return new Random();
  }




}