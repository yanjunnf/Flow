package com.flow;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;
import com.wordnik.swagger.jaxrs.config.BeanConfig;

import com.flow.CustomContainerResponseFilter;
import com.flow.resource.FlowResource;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.ContextResolver;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/flow/";

    /**.
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), createApp());
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
	public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        BeanConfig beanConfig = null;
        beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0");
        beanConfig.setScan(true);
        beanConfig.setResourcePackage(FlowResource.class.getPackage().getName());
        beanConfig.setBasePath(BASE_URI);
        beanConfig.setDescription("Flow");
        beanConfig.setTitle("Flow APIs");
        System.out.println(String.format("Async web parser app started with WADL available at "
                + "%sapplication.wadl", BASE_URI));
        System.out.println(String.format("You can view the all rest APIs at  "
                + "%sapi-docs\n", BASE_URI));
        System.in.read();
        server.shutdownNow();
    }
    
    public static ResourceConfig createApp() {
        return new ResourceConfig().
                packages(FlowResource.class.getPackage().getName(),
                        "com.wordnik.swagger.jaxrs.listing").
                register(createMoxyJsonResolver()).
                register(CustomContainerResponseFilter.class);
                
    }
    
    public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper
                = new HashMap<String, String>(1);
        namespacePrefixMapper.put(
                "http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper)
                .setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }
}

