package br.com.cep.repository.client;

import br.com.cep.repository.client.resources.Cep;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class CepClientTest {
    @Test
    public void assertThatFindCepIsWorkingProperly() throws IOException {
        String cepUrl = "http://mock-url.org/api";

        ObjectMapper objectMapper = mock(ObjectMapper.class);
        HttpClient httpClient = mock(HttpClient.class);

        CepClient cepClient = new CepClient();
        cepClient.setCepUrl(cepUrl);
        cepClient.setHttpClient(httpClient);
        cepClient.setObjectMapper(objectMapper);

        final HttpResponse httpResponse = mock(HttpResponse.class);
        final HttpEntity httpEntity = mock(HttpEntity.class);
        final InputStream inputStream = mock(InputStream.class);


        final Cep.Builder cepBuilder = Cep.newCep();

        Cep cepMock = cepBuilder
                        .cep("01304000")
                        .bairro("Consolação")
                        .tipoLogradouro("Rua")
                        .logradouro("Augusta")
                        .uf("SP")
                        .cidade("São Paulo")
                    .build();

        when(httpResponse.getEntity()).thenReturn(httpEntity);
        when(httpEntity.getContent()).thenReturn(inputStream);
        when(httpClient.execute(any(HttpGet.class))).thenReturn(httpResponse);
        when(objectMapper.readValue(inputStream, Cep.class)).thenReturn(cepMock);

        final Cep cep = cepClient.findCep("01304000");
        assertThat(cep, equalTo(cepMock));
    }
}