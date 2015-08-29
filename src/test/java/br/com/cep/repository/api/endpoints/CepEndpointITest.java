package br.com.cep.repository.api.endpoints;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.ws.rs.core.MediaType;

import static br.com.cep.repository.Application.startContainer;
import static br.com.cep.repository.Application.stopContainer;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;


/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class CepEndpointITest {
    @BeforeClass
    public static void before() throws ServletException {
        startContainer();
    }

    @AfterClass
    public static void after(){
        stopContainer();
    }

    @Test
    public void assertThatReturns200(){
        when()
            .get("http://localhost:8080/cep-repository/api/v1/cep/01304000")
        .then()
            .assertThat()
                .statusCode(200)
            .and()
                .contentType(MediaType.APPLICATION_JSON)
            .and().assertThat()
                .body("item.cidade", notNullValue())
            .and()
                .body("item.uf", notNullValue())
            .and()
                .body("item.cep", notNullValue())
            .and()
                .body("item.bairro", notNullValue())
            .and()
                .body("item.logradouro", notNullValue());

    }

    @Test
    public void assertThatReturns400WhenCepIsInvalid(){
        when()
            .get("http://localhost:8080/cep-repository/api/v1/cep/0130400")
        .then()
            .assertThat()
                .statusCode(400)
            .and()
                .contentType(MediaType.APPLICATION_JSON)
            .and().assertThat()
                .body("error.message", equalTo("CEP inválido"));

    }

    @Test
    public void assertThatReturns404WhenNoCloseCepIsFound(){
        when()
            .get("http://localhost:8080/cep-repository/api/v1/cep/00304000")
        .then()
            .assertThat()
                .statusCode(404)
            .and()
                .contentType(MediaType.APPLICATION_JSON)
            .and().assertThat()
                .body("error.message", equalTo("CEP não encontrado"));
    }


    @Test
    public void assertThatTheClosestCepIsReturnedWhenGivenCepNotFound(){
        when()
            .get("http://localhost:8080/cep-repository/api/v1/cep/99999999")
        .then()
            .assertThat()
                .statusCode(200)
            .and()
                .contentType(MediaType.APPLICATION_JSON)
            .and().assertThat()
                .body("item.cep", equalTo("99990000"))
            .and()
                .body("item.cidade", notNullValue())
            .and()
                .body("item.uf", notNullValue())
            .and()
                .body("item.bairro", notNullValue())
            .and()
                .body("item.logradouro", notNullValue());
    }
}