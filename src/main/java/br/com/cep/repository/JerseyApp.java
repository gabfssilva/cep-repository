package br.com.cep.repository;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationPath("/api/*")
public class JerseyApp extends ResourceConfig {
    public JerseyApp() {
        packages(true, "br.com.cep.repository");
    }
}