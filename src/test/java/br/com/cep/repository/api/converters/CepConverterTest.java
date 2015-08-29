package br.com.cep.repository.api.converters;

import br.com.cep.repository.api.resources.CepResource;
import br.com.cep.repository.client.resources.Cep;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class CepConverterTest {
    private CepConverter cepConverter;

    @Test
    public void assertCepConversionIsWorking() {
        cepConverter = new CepConverter();

        final Cep.Builder cepBuilder = Cep.newCep();

        Cep cepMock = cepBuilder
                            .cep("01304000")
                            .bairro("Consolação")
                            .tipoLogradouro("Rua")
                            .logradouro("Augusta")
                            .uf("SP")
                            .cidade("São Paulo")
                        .build();

        final CepResource cep = cepConverter.convert(cepMock);

        assertThat(cep.getBairro(), equalTo(cepMock.getBairro()));
        assertThat(cep.getCidade(), equalTo(cepMock.getCidade()));
        assertThat(cep.getUf(), equalTo(cepMock.getUf()));
        assertThat(cep.getLogradouro(), equalTo(cepMock.getTipoLogradouro() + " " + cepMock.getLogradouro()));
        assertThat(cep.getCep(), equalTo(cepMock.getCep()));
    }
}