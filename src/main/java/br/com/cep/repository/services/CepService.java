package br.com.cep.repository.services;

import br.com.cep.repository.client.CepClient;
import br.com.cep.repository.client.resources.Cep;
import br.com.cep.repository.interceptors.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
@Log
public class CepService {
    public static final int LAST_INDEX = 7;

    private static final Logger logger = LoggerFactory.getLogger(CepService.class);

    @Inject
    private CepClient cepClient;

    private Cep findClosestCep(String cep, int index){
        final Cep cepObject = cepClient.findCep(cep);

        if(cepObject == null){
            final StringBuilder builder = new StringBuilder(cep);

            if(index < 0){
                logger.warn("CEP " + cep + " not found");
                return null;
            }

            while(zeroIsTheNextItem(index, builder)){
                --index;

                if(index < 0){
                    return null;
                }
            }

            builder.setCharAt(index, '0');
            final String newCep = builder.toString();
            logger.warn("CEP " + cep + " not found, trying the closest: " + newCep);
            return findClosestCep(newCep, index);
        }

        cepObject.setCep(cep);
        return cepObject;
    }

    public Cep findClosestCep(String cep){
        return findClosestCep(cep, LAST_INDEX);
    }

    private boolean zeroIsTheNextItem(int index, StringBuilder builder) {
        return '0' == builder.charAt(index);
    }

    public void setCepClient(CepClient cepClient) {
        this.cepClient = cepClient;
    }
}
