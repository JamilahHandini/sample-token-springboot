package com.tujuhsembilan.library.utility;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class is an utility that simplify usage
 * of frequently used Apache Commons Digest Utility
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashUtil {

    // #region Secure 256 Bit Hash
    /**
     * SHA_256 Digest Utility
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SHA_256 {

        public static byte[] digest(String data) {
            return DigestUtils.sha256(data);
        }

        public static byte[] digest(byte[] data) {
            return DigestUtils.sha256(data);
        }

        public static String digestAsHex(String data) {
            return DigestUtils.sha256Hex(data);
        }

        public static String digestAsHex(byte[] data) {
            return DigestUtils.sha256Hex(data);
        }

        public static byte[] digestAsBase64(String data) {
            return Base64.encodeBase64(digest(data));
        }

        public static byte[] digestAsBase64(byte[] data) {
            return Base64.encodeBase64(digest(data));
        }

        public static String digestAsBase64String(String data) {
            return Base64.encodeBase64String(digest(data));
        }

        public static String digestAsBase64String(byte[] data) {
            return Base64.encodeBase64String(digest(data));
        }

    }

    /**
     * SHA3_256 Digest Utility
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SHA3_256 {

        public static byte[] digest(String data) {
            return DigestUtils.sha3_256(data);
        }

        public static byte[] digest(byte[] data) {
            return DigestUtils.sha3_256(data);
        }

        public static String digestAsHex(String data) {
            return DigestUtils.sha3_256Hex(data);
        }

        public static String digestAsHex(byte[] data) {
            return DigestUtils.sha3_256Hex(data);
        }

        public static byte[] digestAsBase64(String data) {
            return Base64.encodeBase64(digest(data));
        }

        public static byte[] digestAsBase64(byte[] data) {
            return Base64.encodeBase64(digest(data));
        }

        public static String digestAsBase64String(String data) {
            return Base64.encodeBase64String(digest(data));
        }

        public static String digestAsBase64String(byte[] data) {
            return Base64.encodeBase64String(digest(data));
        }

    }
    // #endregion

    // #region Secure 512 Bit Hash
    /**
     * SHA_512 Digest Utility
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SHA_512 {

        public static byte[] digest(String data) {
            return DigestUtils.sha512(data);
        }

        public static byte[] digest(byte[] data) {
            return DigestUtils.sha512(data);
        }

        public static String digestAsHex(String data) {
            return DigestUtils.sha512Hex(data);
        }

        public static String digestAsHex(byte[] data) {
            return DigestUtils.sha512Hex(data);
        }

        public static byte[] digestAsBase64(String data) {
            return Base64.encodeBase64(digest(data));
        }

        public static byte[] digestAsBase64(byte[] data) {
            return Base64.encodeBase64(digest(data));
        }

        public static String digestAsBase64String(String data) {
            return Base64.encodeBase64String(digest(data));
        }

        public static String digestAsBase64String(byte[] data) {
            return Base64.encodeBase64String(digest(data));
        }

    }

    /**
     * SHA3_512 Digest Utility
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SHA3_512 {

        public static byte[] digest(String data) {
            return DigestUtils.sha3_512(data);
        }

        public static byte[] digest(byte[] data) {
            return DigestUtils.sha3_512(data);
        }

        public static String digestAsHex(String data) {
            return DigestUtils.sha3_512Hex(data);
        }

        public static String digestAsHex(byte[] data) {
            return DigestUtils.sha3_512Hex(data);
        }

        public static byte[] digestAsBase64(String data) {
            return Base64.encodeBase64(digest(data));
        }

        public static byte[] digestAsBase64(byte[] data) {
            return Base64.encodeBase64(digest(data));
        }

        public static String digestAsBase64String(String data) {
            return Base64.encodeBase64String(digest(data));
        }

        public static String digestAsBase64String(byte[] data) {
            return Base64.encodeBase64String(digest(data));
        }

    }
    // #endregion

    // #region Message Digest 5 Hash
    /**
     * MD5 Digest Utility
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MD5 {

        public static byte[] digest(String data) {
            return DigestUtils.md5(data);
        }

        public static byte[] digest(byte[] data) {
            return DigestUtils.md5(data);
        }

        public static String digestAsHex(String data) {
            return DigestUtils.md5Hex(data);
        }

        public static String digestAsHex(byte[] data) {
            return DigestUtils.md5Hex(data);
        }

        public static byte[] digestAsBase64(String data) {
            return Base64.encodeBase64(digest(data));
        }

        public static byte[] digestAsBase64(byte[] data) {
            return Base64.encodeBase64(digest(data));
        }

        public static String digestAsBase64String(String data) {
            return Base64.encodeBase64String(digest(data));
        }

        public static String digestAsBase64String(byte[] data) {
            return Base64.encodeBase64String(digest(data));
        }

    }
    // #endregion

}