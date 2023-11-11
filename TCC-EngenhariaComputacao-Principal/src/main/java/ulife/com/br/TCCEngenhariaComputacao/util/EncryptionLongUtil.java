package ulife.com.br.TCCEngenhariaComputacao.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionLongUtil {

    static String IV ="AAAAAAAAAAAAAAAA";

    public static String encrypt(Long data,String encryptionKey) throws Exception {
        Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8),"AES");

        encrypt.init(Cipher.ENCRYPT_MODE,keySpec,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] textoEncriptado = encrypt.doFinal(longToBytes(data));

        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    public static Long decrypt(String encryptedText,String encryptionKey) throws Exception {
        Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8),"AES");

        decrypt.init(Cipher.DECRYPT_MODE,keySpec,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] textoEncriptado = Base64.getDecoder().decode(encryptedText);

        return byteToLong(decrypt.doFinal(textoEncriptado));
    }

    public static byte[] longToBytes(Long data){
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(data);
        return buffer.array();
    }

    private static Long byteToLong(byte[] data){
        ByteBuffer buffer = ByteBuffer.wrap(data);
        return buffer.getLong();
    }
}
