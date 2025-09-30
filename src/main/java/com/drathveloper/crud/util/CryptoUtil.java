package com.drathveloper.crud.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CryptoUtil {
    private static final int GCM_TAG_LENGTH = 16;

    public static String decryptContent(String ciphertextStr, String keyStr) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(keyStr);
        byte[] ciphertext = Base64.getDecoder().decode(ciphertextStr);
        SecretKey key = new SecretKeySpec(decodedKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        int nonceSize = 12;
        if (ciphertext.length < nonceSize) {
            throw new IllegalArgumentException("ciphertext too short: " + ciphertext.length);
        }
        byte[] nonce = new byte[nonceSize];
        System.arraycopy(ciphertext, 0, nonce, 0, nonceSize);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);
        byte[] plaintext = cipher.doFinal(ciphertext, nonceSize, ciphertext.length - nonceSize);
        return new String(plaintext, StandardCharsets.UTF_8);
    }
}
