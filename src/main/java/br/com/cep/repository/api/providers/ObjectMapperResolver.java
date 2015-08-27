package br.com.cep.repository.api.providers;

import br.com.cep.repository.util.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return ObjectMapperFactory.get();
    }
}