package ulife.com.br.TCCEngenhariaComputacao.config.encrypt;

import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ulife.com.br.TCCEngenhariaComputacao.util.EncryptionStringUtil;

@Component
public class EncryptString implements AttributeConverter<String,String> {

    @Value("${tecc.encryption.key}")
    String encyptionKey = "";

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try{
            return EncryptionStringUtil.encrypt(attribute,encyptionKey);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try{
            return EncryptionStringUtil.decrypt(dbData,encyptionKey);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
