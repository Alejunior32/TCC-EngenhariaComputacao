package ulife.com.br.TCCEngenhariaComputacao.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionStringUtil {

    static String IV ="AAAAAAAAAAAAAAAA";

    public static String encrypt(String data,String encryptionKey) throws Exception {
        Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8),"AES");

        encrypt.init(Cipher.ENCRYPT_MODE,keySpec,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] textoEncriptado = encrypt.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    public static String decrypt(String encryptedText,String encryptionKey) throws Exception {
        Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8),"AES");

        decrypt.init(Cipher.DECRYPT_MODE,keySpec,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] textoEncriptado = Base64.getDecoder().decode(encryptedText);

        return new String(decrypt.doFinal(textoEncriptado),StandardCharsets.UTF_8);
    }

}
