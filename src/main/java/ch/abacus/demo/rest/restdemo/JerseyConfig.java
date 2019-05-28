package ch.abacus.demo.rest.restdemo;

import ch.abacus.demo.rest.restdemo.controller.AddressControler;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(AddressControler.class);
    }
}
