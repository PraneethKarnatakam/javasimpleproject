package com.zoomcar;

import com.zoomcar.health.TemplateHealthCheck;
import com.zoomcar.resources.CarGroupResource;
import com.zoomcar.resources.DemoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class javasimpleprojectApplication extends Application<javasimpleprojectConfiguration> {

    public static void main(final String[] args) throws Exception {
        new javasimpleprojectApplication().run(args);
    }

    @Override
    public String getName() {
        return "java-simple-project";
    }

    @Override
    public void run(javasimpleprojectConfiguration javasimpleprojectConfiguration, Environment environment)  {
        DemoResource demoResource = new DemoResource(javasimpleprojectConfiguration.getTemplate(), javasimpleprojectConfiguration.getDefaultName());

         TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(javasimpleprojectConfiguration.getTemplate());
        DAOModule daoModule = DAOModule.getInstance();
        daoModule.init(javasimpleprojectConfiguration, environment);
        CarGroupResource carGroupResource = new CarGroupResource();
        environment.jersey().register(carGroupResource);

        environment.jersey().register(demoResource);
        environment.healthChecks().register("template",healthCheck);
    }

    @Override
    public void initialize(final Bootstrap<javasimpleprojectConfiguration> bootstrap) {
        // TODO: application initialization
    }



}
