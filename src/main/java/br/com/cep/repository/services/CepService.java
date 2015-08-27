package br.com.cep.repository.services;

import br.com.cep.repository.client.CepClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class CepService {
    public static final int LAST_INDEX = 8;

    @Inject
    private CepClient cepClient;

    private Map<String, Object> findClosestCep(String cep, int index){
        final Map<String, Object> cepJsonObject = cepClient.findCep(cep);

        if(cepJsonObject == null){
            final StringBuilder builder = new StringBuilder(cep);

            --index;

            if(index < 0){
                return null;
            }

            while(index >= 1 && '0' == builder.charAt(index)){
                --index;
            }

            builder.setCharAt(index, '0');
            return findClosestCep(builder.toString(), index);
        }

        return cepJsonObject;
    }

    public Map<String, Object> findClosestCep(String cep){
        return findClosestCep(cep, LAST_INDEX);
    }
}
