package br.com.cep.repository.services;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class CepValidator {
    public boolean isValid(String cep){
        return ("".equals(cep) || cep == null || cep.length() != 8);
    }
}
