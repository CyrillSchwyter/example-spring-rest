package ch.abacus.demo.rest.restdemo.controller;

import ch.abacus.demo.rest.restdemo.repository.AddressRepository;
import ch.abacus.demo.rest.restdemo.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.Collection;

@Component
@Path("/addresses")
public class AddressControler {

    @Autowired
    private AddressRepository addressRepository;


    @GET
    @Produces("text/plain")
    @Path("/test")
    public String sayHello() {

        return "Hello du";
    }

    @GET
    @Produces("application/json")
    @Path("/")
    public Collection<Address> getAddresses() {
        return addressRepository.getAllAddresses();
    }

    @GET
    @Produces("application/json")
    @Path("/{oid}")
    public Address getAddress(@PathParam("oid") Long id) {

        Address address = new Address();
        address.setId(id);
        address.setName("Cyrill");
        return address;
    }
}
