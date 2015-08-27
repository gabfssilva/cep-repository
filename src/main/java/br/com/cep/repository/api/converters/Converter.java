package br.com.cep.repository.api.converters;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public interface Converter<T, R> {
    R convert(T obj);
}
