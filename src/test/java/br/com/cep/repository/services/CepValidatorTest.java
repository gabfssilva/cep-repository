package br.com.cep.repository.services;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class CepValidatorTest {
    private CepValidator cepValidator = new CepValidator();

    @Test
    public void shouldReturnFalse() {
        assertFalse(cepValidator.isInvalid("01304000"));
    }

    @Test
    public void shouldReturnTrueBecauseItHas7Chars() {
        assertTrue(cepValidator.isInvalid("0130400"));
    }

    @Test
    public void shouldReturnTrueBecauseItHas9Chars() {
        assertTrue(cepValidator.isInvalid("013040000"));
    }

    @Test
    public void shouldReturnTrueBecauseItHas0Chars() {
        assertTrue(cepValidator.isInvalid(""));
    }

    @Test
    public void shouldReturnTrueBecauseItIsNull() {
        assertTrue(cepValidator.isInvalid(null));
    }
}