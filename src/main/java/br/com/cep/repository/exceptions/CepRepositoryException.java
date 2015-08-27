package br.com.cep.repository.exceptions;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class CepRepositoryException extends RuntimeException {
    public CepRepositoryException() {
    }

    public CepRepositoryException(String message) {
        super(message);
    }

    public CepRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public CepRepositoryException(Throwable cause) {
        super(cause);
    }

    public CepRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
