package com.flow;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.flow.resource.ActionResource;
import com.wordnik.swagger.jaxrs.config.BeanConfig;

public class Bootstrap extends HttpServlet {
    private static final long serialVersionUID = -4021857025876915229L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.1.1");
        beanConfig.setScan(true);
        beanConfig.setResourcePackage(ActionResource.class.getPackage().getName());
        beanConfig.setBasePath(ConfigClass.baseUrl);
        beanConfig.setDescription("Async Web Parser");
        beanConfig.setTitle("Async Web Parser API Docs");
    }
}