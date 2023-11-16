package com.tujuhsembilan.library.utility;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CipherUtil {

  @RequiredArgsConstructor
  public static class ChaCha20Poly1305 {

    private static final String ENCRYPT_ALGO = "ChaCha20-Poly1305";
    private static final Integer STRENGTH = 12;

    private final SecretKeySpec key;

    private static byte[] iv() {
      byte[] newNonce = new byte[STRENGTH];
      new SecureRandom().nextBytes(newNonce);
      return newNonce;
    }

    @SuppressWarnings("java:S1160")
    public byte[] encrypt(byte[] pText) throws InvalidKeyException, NoSuchAlgorithmException,
        NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
      return encrypt(pText, iv());
    }

    @SuppressWarnings("java:S1160")
    public byte[] encrypt(byte[] pText, byte[] nonce)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
      @SuppressWarnings("java:S5542")
      Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

      @SuppressWarnings("java:S3329")
      IvParameterSpec iv = new IvParameterSpec(nonce);

      cipher.init(Cipher.ENCRYPT_MODE, key, iv);

      byte[] encryptedText = cipher.doFinal(pText);

      return ByteBuffer.allocate(encryptedText.length + STRENGTH)
          .put(encryptedText)
          .put(nonce)
          .array();
    }

    @SuppressWarnings("java:S1160")
    public byte[] decrypt(byte[] cText) throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
      ByteBuffer bb = ByteBuffer.wrap(cText);

      byte[] encryptedText = new byte[cText.length - STRENGTH];
      byte[] nonce = new byte[STRENGTH];
      bb.get(encryptedText);
      bb.get(nonce);

      @SuppressWarnings("java:S5542")
      Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

      @SuppressWarnings("java:S3329")
      IvParameterSpec iv = new IvParameterSpec(nonce);

      cipher.init(Cipher.DECRYPT_MODE, key, iv);

      return cipher.doFinal(encryptedText);
    }

  }

}