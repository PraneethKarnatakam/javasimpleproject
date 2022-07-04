package com.zoomcar.debezium;

import io.debezium.config.Configuration;


public class DebeziumConnector
{
    private static DebeziumConnector debeziumConnector = new DebeziumConnector();
    public static Configuration configuration = getDebeziumConfig();
    public static DebeziumConnector getInstance() {
        System.out.println("hey I am getting debezium connector");
        return debeziumConnector;
    }
    private DebeziumConnector()
    {

    }

    private static Configuration getDebeziumConfig()
    {
        return Configuration.create()
                .with("name", "database-mysql-connector")
                .with("connector.class", "io.debezium.connector.mysql.MySqlConnector")
                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
                .with("offset.storage.file.filename", "/tmp/offsets.dat")
                .with("offset.flush.interval.ms", "60000")
                .with("database.hostname", "common-staging-database.zoomcartest.com")
                .with("database.port", "3306")
                .with("database.user", "deploy")
                .with("database.password", "NzE4OWRiMjZlMDRlZG")
                .with("database.dbname", "customerDbName")
                .with("database.include.list", "customerDbName")
                .with("include.schema.changes", "false")
                .with("database.server.id", "10181")
                .with("database.server.name", "customer-mysql-db-server")
                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
                .with("database.history.file.filename", "/tmp/dbhistory.dat")
                .build();
    }

}
