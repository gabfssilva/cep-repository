package br.com.cep.repository;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.Listener;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.listener;
import static io.undertow.servlet.Servlets.servlet;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Application {
    public static void main(String[] args) throws ServletException {
        DeploymentInfo servletBuilder = Servlets.deployment();

        servletBuilder
                .setClassLoader(Application.class.getClassLoader())
                .setContextPath("/cep-repository")
                .setDeploymentName("cep-repository.war")
                .addListeners(listener(Listener.class))
                .addServlets(servlet("jerseyServlet", ServletContainer.class)
                        .setLoadOnStartup(1)
                        .addInitParam("javax.ws.rs.Application", JerseyApp.class.getName())
                        .addMapping("/api/*"));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();
        PathHandler path = Handlers.path(Handlers.redirect("/cep-repository"))
                .addPrefixPath("/cep-repository", manager.start());

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(path)
                .build();

        server.start();
    }
}