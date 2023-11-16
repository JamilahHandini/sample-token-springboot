package com.tujuhsembilan.library.configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

import com.tujuhsembilan.library.configuration.property.CipherProp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CipherConfig {

  private static byte[] getDigestedPassword(CipherProp cipherProp) throws NoSuchAlgorithmException {
    return Arrays.copyOf(
        MessageDigest.getInstance("SHA-384").digest(
            cipherProp.getSecret().getBytes(StandardCharsets.UTF_8)),
        32);
  }

  @Bean
  public SecretKeySpec secretKeyAes(CipherProp cipherProp) throws NoSuchAlgorithmException {
    return new SecretKeySpec(
        getDigestedPassword(cipherProp),
        "AES");

  }

  @Bean
  public SecretKeySpec secretKeyChaCha(CipherProp cipherProp) throws NoSuchAlgorithmException {
    return new SecretKeySpec(
        getDigestedPassword(cipherProp),
        "ChaCha20-Poly1305");

  }

}