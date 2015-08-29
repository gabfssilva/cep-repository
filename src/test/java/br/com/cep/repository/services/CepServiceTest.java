package br.com.cep.repository.services;

import br.com.cep.repository.client.CepClient;
import br.com.cep.repository.client.resources.Cep;
import org.junit.Test;
import org.mockito.Mock;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class CepServiceTest {
    @Test
    public void assertThatTheCepIsReturned(){
        CepService cepService = new CepService();
        CepClient cepClient = mock(CepClient.class);
        cepService.setCepClient(cepClient);
        final Cep cep = mock(Cep.class);

        when(cepClient.findCep("01304000")).thenReturn(cep);
        final Cep closestCep = cepService.findClosestCep("01304000");
        assertThat(closestCep, equalTo(cep));
    }

    @Test
    public void assertThatTheClosestCepIsReturned(){
        CepService cepService = new CepService();
        CepClient cepClient = mock(CepClient.class);
        cepService.setCepClient(cepClient);
        final Cep cep = mock(Cep.class);

        when(cepClient.findCep("99999999")).thenReturn(null);
        when(cepClient.findCep("99999990")).thenReturn(null);
        when(cepClient.findCep("99999900")).thenReturn(cep);

        final Cep closestCep = cepService.findClosestCep("99999999");
        assertThat(closestCep, equalTo(cep));
    }

    @Test
    public void assertThatNoCepIsReturned(){
        CepService cepService = new CepService();
        CepClient cepClient = mock(CepClient.class);
        cepService.setCepClient(cepClient);

        final Cep closestCep = cepService.findClosestCep("99999999");
        assertThat(closestCep, equalTo(null));
    }
}