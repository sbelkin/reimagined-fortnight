package io.elixir.backend;

import com.yahoo.elide.contrib.dropwizard.elide.ElideBundle;
import com.yahoo.elide.resources.JsonApiEndpoint;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.elixir.backend.configuration.ServiceConfiguration;
import io.elixir.entities.model.Beer;
import io.elixir.entities.model.Company;
import io.elixir.entities.model.User;

/**
 * Created by sbelkin on 11/6/2016.
 */
public class ServiceApplication extends Application<ServiceConfiguration> {

    public static void main(String[] args)throws Exception {
        new ServiceApplication().run(args);
    }

    private final ElideBundle<ServiceConfiguration> bundle;

    public ServiceApplication(){
        bundle = new ElideBundle<ServiceConfiguration>(
                Beer.class,
                Company.class,
                User.class) {
            public PooledDataSourceFactory getDataSourceFactory(ServiceConfiguration serviceConfiguration) {
                return serviceConfiguration.getDataSourceFactory();
            }
        };
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.addBundle(bundle);
    }

    public void run(ServiceConfiguration serviceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(JsonApiEndpoint.class);
    }

}
