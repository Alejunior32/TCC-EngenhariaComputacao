package ulife.com.br.TCCEngenhariaComputacao.util;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionDoubleUtil {

    static String IV ="AAAAAAAAAAAAAAAA";

    public static String encrypt(Double data,String encryptionKey) throws Exception {
        Cipher encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8),"AES");

        encrypt.init(Cipher.ENCRYPT_MODE,keySpec,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] textoEncriptado = encrypt.doFinal(doubleToBytes(data));

        return Base64.getEncoder().encodeToString(textoEncriptado);
    }

    public static Double decrypt(String encryptedText,String encryptionKey) throws Exception {
        Cipher decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding","SunJCE");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8),"AES");

        decrypt.init(Cipher.DECRYPT_MODE,keySpec,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        byte[] textoEncriptado = Base64.getDecoder().decode(encryptedText);

        return bytesToDouble(decrypt.doFinal(textoEncriptado));
    }

    private static byte[] doubleToBytes(Double data){
        long longBits = Double.doubleToLongBits(data);
        byte[] byteArray = new byte[8];

        for (int i = 7;i>= 0; i--){
            byteArray[i] = (byte) (longBits & 0xFF);
            longBits >>= 8;
        }

        return byteArray;
    }

    private static  Double bytesToDouble(byte[] bytes){
        if (bytes.length != 8)
            throw new IllegalArgumentException("Byte array must be of length 8");

        long longBits = 0;
        for (int i = 0; i < 8; i++){
            longBits <<= 8;
            longBits |= (bytes[i] & 0xFF);
        }

        return Double.longBitsToDouble(longBits);

    }
}
