package util;

import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtil {
    public static String md5(String str) {
        Logger.getLogger(EncryptionUtil.class).info("Password encryption process");

        MessageDigest md5 = null;
        byte[] bytes = new byte[0];

        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            bytes = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes){
            builder.append(String.format("%02x",b));
        }

        return builder.toString();
    }
}
