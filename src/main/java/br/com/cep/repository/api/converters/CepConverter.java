package br.com.cep.repository.api.converters;

import br.com.cep.repository.api.model.Cep;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class CepConverter implements Converter<Map<String, Object>, Cep> {
    @Override
    public Cep convert(Map<String, Object> obj) {
        return Cep.newBuilder()
                    .bairro((String) obj.get("bairro"))
                    .cep((String) obj.get("cep"))
                    .cidade((String) obj.get("localidade"))
                    .complemento((String) obj.get("complemento"))
                    .uf((String) obj.get("uf"))
                    .logradouro((String) obj.get("logradouro"))
                .build();
    }
}
