package com.zoomcar;

import com.zoomcar.debezium.DebeziumListener;
import com.zoomcar.health.TemplateHealthCheck;
import com.zoomcar.resources.CarGroupResource;
import com.zoomcar.resources.DemoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.debezium.config.Configuration;

import java.io.File;
import java.io.IOException;

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

        Configuration configuration = Configuration.create()
                .with("name", "database-mysql-connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/tmp/offsets.dat")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", "common-staging-database.zoomcartest.com")
                .with("database.port", "3306")
                .with("database.user", "deploy")
                .with("database.password", "NzE4OWRiMjZlMDRlZG")
                .with("database.dbname", "inventory3")
                .with("database.include.list", "inventory3")
                .with("include.schema.changes", "false")
                .with("database.server.id", "10181")
                .with("database.server.name", "customer-mysql-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "dbhistory.dat")
                .build();
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        DebeziumListener debeziumListener = new DebeziumListener(configuration);
        debeziumListener.init();



        environment.jersey().register(carGroupResource);

        environment.jersey().register(demoResource);
        environment.healthChecks().register("template",healthCheck);
    }

    @Override
    public void initialize(final Bootstrap<javasimpleprojectConfiguration> bootstrap) {
        // TODO: application initialization
    }



}
