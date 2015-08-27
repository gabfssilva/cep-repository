package br.com.cep.repository.api.model;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Envelop {
    private String uri;
    private Object item;
    private ErrorMessage error;

    private Envelop(Builder builder) {
        uri = builder.uri;
        item = builder.item;
        error = builder.error;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Object getItem() {
        return item;
    }

    public void setItem(Object item) {
        this.item = item;
    }

    public ErrorMessage getError() {
        return error;
    }

    public void setError(ErrorMessage error) {
        this.error = error;
    }

    public static Builder newEnvelop() {
        return new Builder();
    }

    public static class ErrorMessage {
        private String message;

        public ErrorMessage(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static final class Builder {
        private String uri;
        private Object item;
        private ErrorMessage error;

        private Builder() {
        }

        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder item(Object item) {
            this.item = item;
            return this;
        }

        public Builder error(ErrorMessage error) {
            this.error = error;
            return this;
        }

        public Envelop build() {
            return new Envelop(this);
        }
    }
}
