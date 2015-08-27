package br.com.cep.repository.client;

import br.com.cep.repository.exceptions.CepRepositoryException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class CepClient {
    @Inject
    private HttpClient httpClient;

    @Inject
    @Named("cepUrl")
    private String cepUrl;

    @Inject
    private ObjectMapper objectMapper;

    public Map<String, Object> findCep(String cep) {
        final String uri = cepUrl + cep + "/json/unicode";
        try {
            final HttpGet httpGet = new HttpGet(uri);
            final HttpResponse response = httpClient.execute(httpGet);
            final HashMap<String, Object> jsonObject = objectMapper.readValue(response.getEntity().getContent(), HashMap.class);
            return(boolean) jsonObject.getOrDefault("erro", false) ? null : jsonObject;
        } catch (IOException e) {
            throw new CepRepositoryException("Could not get from " + uri, e);
        }
    }
}
