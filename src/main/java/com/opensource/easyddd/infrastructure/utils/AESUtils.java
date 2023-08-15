package com.opensource.easyddd.infrastructure.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author mose
 * @description AESUtils
 * @date 2019/10/17 21:11
 * @Reference
 */
public class AESUtils {


    private static final String ALGORITHM_NAME = "AES";

    /**
     * 参数分别代表 算法名称/加密模式/数据填充方式
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    private AESUtils() {
        throw new IllegalStateException("Utility class");
    }

   public static String encrypt(String content, String encryptKey) throws Exception {
       Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
       cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), ALGORITHM_NAME));
       byte[] bytes = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
       // 采用base64算法进行转码,避免出现中文乱码
       return Base64.getEncoder().encodeToString(bytes);
   }

    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), ALGORITHM_NAME));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.getDecoder().decode(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }
}
