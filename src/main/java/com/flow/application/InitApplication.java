package com.flow.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.flow.config.ConfigClass;
import com.flow.resource.ActionResource;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.ContextResolver;
/**
 * Servlet implementation class InitApplication 
 */
public class InitApplication extends ResourceConfig {
    public static boolean bInRealServer = false;
    public static String startTime = "";

    public InitApplication() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            startTime = df.format(new Date());
            
            System.out.println(String.format("Async web parser app started with WADL available at "
                    + "%s/application.wadl", ConfigClass.baseUrl));
            System.out.println(String.format("You can view the all rest APIs at  "
                    + "%s/api-docs\n", ConfigClass.baseUrl));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }


    public static ResourceConfig createApp() {
        return new ResourceConfig().
                packages(ActionResource.class.getPackage().getName(),
                        "com.wordnik.swagger.jaxrs.listing").
                register(createMoxyJsonResolver()).
                register(ContainerResponseFilter.class);
                
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

