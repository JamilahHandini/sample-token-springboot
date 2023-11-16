package com.tujuhsembilan.library.service;

import java.util.Map;

import com.google.auto.value.AutoValue.Builder;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FirebaseMessagingService {

  private final FirebaseMessaging firebaseMessaging;

  @Autowired
  public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
    this.firebaseMessaging = firebaseMessaging;
  }

  @Data
  @Builder
  @AllArgsConstructor
  public static class FirebaseMessagingBody {
    private String title;
    private String body;
    private Map<String, String> data;
  }

  public String sendNotification(String token, FirebaseMessagingBody body) {
    try {
      return firebaseMessaging.send(
          Message.builder()
              .setToken(token)
              .setNotification(
                  Notification.builder()
                      .setTitle(body.getTitle())
                      .setBody(body.getBody())
                      .build())
              .putAllData(body.getData())
              .build());
    } catch (FirebaseMessagingException e) {
      log.debug("Failed to send firebase notification!", e);

      return "ERR: " + e.getMessage();
    }
  }

}