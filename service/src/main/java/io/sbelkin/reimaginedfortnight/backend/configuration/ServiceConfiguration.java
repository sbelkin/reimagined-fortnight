package io.sbelkin.reimaginedfortnight.backend.configuration;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sbelkin on 11/6/2016.
 */
public class ServiceConfiguration extends Configuration {

    @NotEmpty
    @JsonProperty("googleKey")
    private String googleKey;

    @JsonProperty("database")
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    public String getGoogleKey() {
        return googleKey;
    }

    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }
}
