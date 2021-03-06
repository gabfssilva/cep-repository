package br.com.cep.repository.api.endpoints;

import br.com.cep.repository.api.converters.CepConverter;
import br.com.cep.repository.api.resources.Envelop;
import br.com.cep.repository.client.resources.Cep;
import br.com.cep.repository.interceptors.Log;
import br.com.cep.repository.services.CepService;
import br.com.cep.repository.services.CepValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static br.com.cep.repository.api.resources.Envelop.newEnvelop;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
@Path("/v1/cep")
@Produces("application/json")
@Log
public class CepEndpoint {
    @Inject
    private CepService cepService;

    @Inject
    private CepConverter cepConverter;

    @Inject
    private CepValidator cepValidator; //poderia usar bean validation, mas, é uma validação muito simples, não vale a pena

    @GET
    @Path("/{cep}")
    public Response get(@PathParam("cep") String cep){
        cep = cep.replace("-", "");

        if(cepValidator.isInvalid(cep)){
            return status(400)
                        .entity(newEnvelop()
                                .error(new Envelop.ErrorMessage("CEP inválido"))
                                .build())
                    .build();
        }

        final Cep closestCep = cepService.findClosestCep(cep); //poderia haver alguma lógica para se caso não haja o cep buscado, retorne um 303 com o mais próximo

        if(closestCep == null){
            return status(404)
                        .entity(newEnvelop()
                                .error(new Envelop.ErrorMessage("CEP não encontrado"))
                                .build())
                    .build();
        }

        return ok(newEnvelop()
                        .item(cepConverter.convert(closestCep))
                        .build())
                    .build();
    }
}
