package com.zerobase.fintech.encrypt;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptComponent {

    private static final String SECRET_KEY = "12345678900987654321123456654321";
    private static final Base64.Encoder encoder = Base64.getEncoder();
    private static final Base64.Decoder decoder = Base64.getDecoder();

    public String encryptString(String item) throws Exception {

        byte[] encryptedString = cipher(Cipher.ENCRYPT_MODE)
                .doFinal(item.getBytes(StandardCharsets.UTF_8));

        return encoder.encodeToString(encryptedString);
    }

    public String decryptString(String item) throws Exception {
        byte[] byteString = decoder.decode(item.getBytes(StandardCharsets.UTF_8));

        byte[] decryptedString = cipher(Cipher.DECRYPT_MODE).doFinal(byteString);

        return new String(decryptedString, StandardCharsets.UTF_8);
    }


    private Cipher cipher(int opMode) throws Exception {
        Cipher c = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec sk = new SecretKeySpec(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        GCMParameterSpec gs =
                new GCMParameterSpec(128, SECRET_KEY.substring(0, 12)
                        .getBytes(StandardCharsets.UTF_8));

        c.init(opMode, sk, gs);

        return c;
    }

}
