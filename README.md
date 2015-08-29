# cep-repository

JDK version: 1.8

Build tool: Gradle

Container: Undertow

Frameworks: JAX-RS (Jersey), CDI (Weld)

##Iniciando a aplicação sem gerar binário
```
./gradlew run
```

##Gerando o binário
```
./gradlew distTar
```

Após realizar o comando, o tar estará disponível em:

```
cep-repository
    - build
        - distributions
            - cep-repository.tar

```

Para iniciar em background:
```
tar -xvf cep-repository.tar
nohup cep-repository/bin/cep-repository &
```

##Entendendo a API

Para chamar os web services é necessário apenas realizar um GET passando o número do CEP:

http://localhost:8080/cep-repository/api/v1/cep/01304000

> Caso o CEP exista, o http status será 200.

```
{
  "item": {
    "cep": "01304000",
    "logradouro": "Rua Auguta",
    "bairro": "Consolação",
    "cidade": "São Paulo",
    "uf": "SP"
  }
}
```

> Caso o CEP seja inválido, será retornado um 400 (bad request)

```
{
  "error": {
    "message": "CEP inválido"
  }
}
```

> Caso não exista, o sistema tentará procurar o CEP mais próximo da sua localidade. Exemplo: se você buscou 99999999 e o CEP não existir, ele irá tentar buscar 99999990, trocando sempre o último número por zeros até achar alguma endereço existente.

```
{
  "item": {
    "cep": "99990000",
    "logradouro": " ",
    "bairro": "",
    "cidade": "Muliterno",
    "uf": "RS"
  }
}
```


> Caso não exista nenhum CEP próximo da localidade, será retornado um http status igual a 404.

```
{
  "error": {
    "message": "CEP nÃ£o encontrado"
  }
}
```