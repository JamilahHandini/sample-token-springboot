package com.tujuhsembilan.library.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("application.cipher")
public class CipherProp {
  private Integer strength = 32;
  private String secret;
}