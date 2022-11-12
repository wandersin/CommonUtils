package vip.mrtree.utils;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA非对称加密
 * <br>
 *
 * @author wangyunshu
 */
public class RSAUtils {
    private static final String ALGORITHM = "RSA";
    private static final int DEFAULT_KEY_LENGTH = 1024;

    /**
     * 生成rsa密钥对
     * <br>
     *
     * @author wangyunshu
     */
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return generateKeyPair(DEFAULT_KEY_LENGTH);
    }

    public static KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGen.initialize(keySize);
        return keyPairGen.generateKeyPair();
    }

    /**
     * 获取公钥字符串
     * <br>
     *
     * @author wangyunshu
     */
    public static String getPublicKey(KeyPair keyPair) {
        return new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
    }

    /**
     * 获取私钥字符串
     * <br>
     *
     * @author wangyunshu
     */
    public static String getPrivateKey(KeyPair keyPair) {
        return new String(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
    }

    /**
     * 公钥加密
     * <br>
     *
     * @author wangyunshu
     */
    public static String encrypt(String data, String publicKey) throws GeneralSecurityException {
        byte[] key = Base64.getDecoder().decode(publicKey);
        PublicKey aPublic = KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, aPublic);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * 私钥解密
     * <br>
     *
     * @author wangyunshu
     */
    public static String decrypt(String data, String privateKey) throws GeneralSecurityException {
        byte[] inputBytes = Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PrivateKey aPrivate = KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, aPrivate);
        return new String(cipher.doFinal(inputBytes));
    }
}
