package br.com.cep.repository.client.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Cep {
    @JsonProperty("resultado")
    private String resultado;
    @JsonProperty("tipo_logradouro")
    private String tipoLogradouro;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("cidade")
    private String cidade;
    @JsonProperty("uf")
    private String uf;

    private String cep;

    public Cep() {
    }

    private Cep(Builder builder) {
        setResultado(builder.resultado);
        setTipoLogradouro(builder.tipoLogradouro);
        setLogradouro(builder.logradouro);
        setBairro(builder.bairro);
        setCidade(builder.cidade);
        setUf(builder.uf);
        setCep(builder.cep);
    }

    public static Builder newCep() {
        return new Builder();
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(String tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Cep{" +
                "tipoLogradouro='" + tipoLogradouro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }


    public static final class Builder {
        private String resultado;
        private String tipoLogradouro;
        private String logradouro;
        private String bairro;
        private String cidade;
        private String uf;
        private String cep;

        private Builder() {
        }

        public Builder resultado(String resultado) {
            this.resultado = resultado;
            return this;
        }

        public Builder tipoLogradouro(String tipoLogradouro) {
            this.tipoLogradouro = tipoLogradouro;
            return this;
        }

        public Builder logradouro(String logradouro) {
            this.logradouro = logradouro;
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

        public Builder cep(String cep) {
            this.cep = cep;
            return this;
        }

        public Cep build() {
            return new Cep(this);
        }
    }
}
