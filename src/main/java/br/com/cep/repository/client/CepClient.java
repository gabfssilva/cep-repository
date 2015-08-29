package br.com.cep.repository.client;

import br.com.cep.repository.client.resources.Cep;
import br.com.cep.repository.exceptions.CepRepositoryException;
import br.com.cep.repository.interceptors.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;


/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Log
@ApplicationScoped
public class CepClient {
    public static final String ERROR_CODE = "0";
    @Inject
    private HttpClient httpClient;

    @Inject
    @Named("cepUrl")
    private String cepUrl;

    @Inject
    private ObjectMapper objectMapper;

    public Cep findCep(String cep) {
        final String uri = cepUrl + "?cep=" + cep + "&formato=json";
        try {
            final HttpGet httpGet = new HttpGet(uri);
            final HttpResponse response = httpClient.execute(httpGet);
            final Cep cepResult = objectMapper.readValue(response.getEntity().getContent(), Cep.class);
            return ERROR_CODE.equals(cepResult.getResultado()) ? null : cepResult;
        } catch (IOException e) {
            throw new CepRepositoryException("Could not get from " + uri, e);
        }
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setCepUrl(String cepUrl) {
        this.cepUrl = cepUrl;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
