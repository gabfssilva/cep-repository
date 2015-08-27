package br.com.cep.repository.api.model;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Cep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    private Cep(Builder builder) {
        cep = builder.cep;
        logradouro = builder.logradouro;
        complemento = builder.complemento;
        bairro = builder.bairro;
        cidade = builder.cidade;
        uf = builder.uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String cep;
        private String logradouro;
        private String complemento;
        private String bairro;
        private String cidade;
        private String uf;

        private Builder() {
        }

        public Builder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public Builder logradouro(String logradouro) {
            this.logradouro = logradouro;
            return this;
        }

        public Builder complemento(String complemento) {
            this.complemento = complemento;
            return this;
        }

        public Builder bairro(String bairro) {
            this.bairro = bairro;
            return this;
        }

        public Builder cidade(String cidade) {
            this.cidade = cidade;
            return this;
        }

        public Builder uf(String uf) {
            this.uf = uf;
            return this;
        }

        public Cep build() {
            return new Cep(this);
        }
    }
}
