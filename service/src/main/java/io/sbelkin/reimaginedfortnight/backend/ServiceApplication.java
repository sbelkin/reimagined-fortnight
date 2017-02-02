package io.sbelkin.reimaginedfortnight.backend;

import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.sbelkin.reimaginedfortnight.backend.configuration.ServiceConfiguration;
import io.sbelkin.reimaginedfortnight.backend.services.ImageService;
import io.sbelkin.reimaginedfortnight.entities.model.Account;
import io.sbelkin.reimaginedfortnight.entities.model.Beer;
import io.sbelkin.reimaginedfortnight.entities.model.Company;
import io.sbelkin.reimaginedfortnight.entities.model.History;

import com.yahoo.elide.contrib.dropwizard.elide.ElideBundle;
import com.yahoo.elide.resources.JsonApiEndpoint;

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
                Account.class,
                Beer.class,
                Company.class,
                History.class) {
            public PooledDataSourceFactory getDataSourceFactory(ServiceConfiguration serviceConfiguration) {
                return serviceConfiguration.getDataSourceFactory();
            }
        };

    }

    @Override
    public String getName() {
        return "reimagined-fortnight";
    }


    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.addBundle(bundle);
    }

    public void run(ServiceConfiguration serviceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(JsonApiEndpoint.class);
        environment.jersey().register(ImageService.class);
//        environment.jersey().register(new AuthDynamicFeature(
//                new OAuthCredentialAuthFilter.Builder<User>()
//                        .setAuthenticator(new ExampleOAuthAuthenticator())
//                        .setAuthorizer(new ServiceAuthorizer())
//                        .setPrefix("Bearer")
//                        .buildAuthFilter()));
//        environment.jersey().register(RolesAllowedDynamicFeature.class);
//        //If you want to use @Auth to inject a custom Principal type into your resource
//        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
//        HashMap checks = new HashMap();
//        checks.put("post is visible now", IsPublished.Inline.class);
//        checks.put("post is visible", IsPublished.AtCommit.class);
//        checks.put("user owns this post now", IsOwner.Inline.class);
//        checks.put("user owns this post", IsOwner.AtCommit.class);
//        checks.put("user is a superuser", IsSuperuser.Inline.class);
////...
//        EntityDictionary dictionary = new EntityDictionary(checks);

    }

}
