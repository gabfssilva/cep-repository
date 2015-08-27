package br.com.cep.repository.api.endpoints;

import br.com.cep.repository.api.converters.CepConverter;
import br.com.cep.repository.api.model.Envelop;
import br.com.cep.repository.services.CepService;
import br.com.cep.repository.services.CepValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

import static br.com.cep.repository.api.model.Envelop.newEnvelop;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
@Path("/v1/cep")
@Produces("application/json")
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

        if(cepValidator.isValid(cep)){
            return status(400)
                        .entity(newEnvelop()
                                .error(new Envelop.ErrorMessage("CEP inválido"))
                                .build())
                    .build();
        }

        final Map<String, Object> closestCep = cepService.findClosestCep(cep); //poderia haver alguma lógica para se caso não haja o cep buscado, retorne um 303 com o mais próximo

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
