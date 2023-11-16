package com.tujuhsembilan.library.configuration;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {

  @Bean
  public FirebaseMessaging firebaseMessaging() throws IOException {
    try {
      return FirebaseMessaging.getInstance(FirebaseApp.getInstance());
    } catch (IllegalStateException err) {
      log.warn("{}: Creating new one!", err.getMessage());

      return FirebaseMessaging.getInstance(
          FirebaseApp.initializeApp(
              FirebaseOptions.builder()
                  .setCredentials(
                      GoogleCredentials.fromStream(
                          new ClassPathResource("firebase/credential.json")
                              .getInputStream()))
                  .build()));
    }
  }

}