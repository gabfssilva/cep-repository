package br.com.cep.repository.api.converters;

import br.com.cep.repository.api.resources.CepResource;
import br.com.cep.repository.client.resources.Cep;

import javax.enterprise.context.ApplicationScoped;

import static br.com.cep.repository.api.resources.CepResource.newCepResource;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class CepConverter implements Converter<Cep, CepResource> {
    @Override
    public CepResource convert(Cep cep) {
        final String logradouro = cep.getTipoLogradouro() == null ? cep.getLogradouro() : cep.getTipoLogradouro() + " " + cep.getLogradouro();

        return newCepResource()
                    .bairro(cep.getBairro())
                    .cep(cep.getCep())
                    .cidade(cep.getCidade())
                    .uf(cep.getUf())
                    .logradouro(logradouro)
                .build();
    }
}
