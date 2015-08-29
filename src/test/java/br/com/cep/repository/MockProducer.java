package br.com.cep.repository;

import br.com.cep.repository.client.resources.Cep;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class MockProducer {

    @Produces
    @Named("validCep")
    public Cep validCep(){
        final Cep.Builder cepBuilder = Cep.newCep();

        return cepBuilder
                        .cep("01304000")
                        .bairro("Consolação")
                        .tipoLogradouro("Rua")
                        .logradouro("Augusta")
                        .uf("SP")
                        .cidade("São Paulo")
                   .build();
    }
}
