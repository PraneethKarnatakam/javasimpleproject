package com.zoomcar;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.constraints.NotEmpty;

public class javasimpleprojectConfiguration extends Configuration {
    // TODO: implement service configuration
    @NotEmpty
    private String template;
    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @JsonProperty
    public String getTemplate() {
        return template;
    }
    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }
    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }
    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
