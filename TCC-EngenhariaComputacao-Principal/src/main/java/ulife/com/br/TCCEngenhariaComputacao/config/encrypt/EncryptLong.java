package ulife.com.br.TCCEngenhariaComputacao.config.encrypt;

import jakarta.persistence.AttributeConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ulife.com.br.TCCEngenhariaComputacao.util.EncryptionLongUtil;

@Component
public class EncryptLong implements AttributeConverter<Long,String> {

    @Value("${tecc.encryption.key}")
    String encyptionKey = "";

    @Override
    public String convertToDatabaseColumn(Long attribute) {
        try{
            return EncryptionLongUtil.encrypt(attribute,encyptionKey);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Long convertToEntityAttribute(String dbData) {
        try{
            return EncryptionLongUtil.decrypt(dbData,encyptionKey);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
