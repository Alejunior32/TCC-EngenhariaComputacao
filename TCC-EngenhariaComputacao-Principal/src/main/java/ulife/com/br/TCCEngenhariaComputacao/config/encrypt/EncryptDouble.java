package ulife.com.br.TCCEngenhariaComputacao.config.encrypt;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ulife.com.br.TCCEngenhariaComputacao.util.EncryptionDoubleUtil;
import ulife.com.br.TCCEngenhariaComputacao.util.EncryptionLongUtil;

@Component
public class EncryptDouble implements AttributeConverter<Double,String> {

    @Value("${tecc.encryption.key}")
    String encyptionKey = "";

    @Override
    public String convertToDatabaseColumn(Double attribute) {
        try{
            return EncryptionDoubleUtil.encrypt(attribute,encyptionKey);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Double convertToEntityAttribute(String dbData) {
        try{
            return EncryptionDoubleUtil.decrypt(dbData,encyptionKey);
        }catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }


}
