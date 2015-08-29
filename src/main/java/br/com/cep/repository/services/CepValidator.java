package br.com.cep.repository.services;

import br.com.cep.repository.interceptors.Log;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
@Log
public class CepValidator {
    public boolean isInvalid(String cep){
        return ("".equals(cep) || cep == null || cep.length() != 8);
    }
}
