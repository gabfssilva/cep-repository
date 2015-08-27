package br.com.cep.repository.producers;

import br.com.cep.repository.util.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import static org.apache.http.impl.client.HttpClientBuilder.create;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class ApplicationProducer {

    @Produces
    @ApplicationScoped
    public HttpClient httpClient() {
        return create()
                .setMaxConnTotal(200)
                .build();
    }

    @Produces
    @Named("cepUrl")
    public String cepUrl() {
        return "http://viacep.com.br/ws/";
    }

    @Produces //não pode ser application scoped porque a classe possui métodos final
    public ObjectMapper objectMapper() {
        return ObjectMapperFactory.get();
    }
}
