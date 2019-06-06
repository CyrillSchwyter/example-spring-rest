package ch.abacus.demo.rest.restdemo.controller;

import ch.abacus.demo.rest.restdemo.model.Address;
import ch.abacus.demo.rest.restdemo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Component
@Path("/addresses")
public class AddressControler {


    private final AddressRepository addressRepository;

    public AddressControler(@Autowired AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public String sayHello() {

        return "Hello du";
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Address> getAddresses() {

        return addressRepository.getAllAddresses();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{oid}")
    public Address getAddress(@PathParam("oid") Long id) {

        return addressRepository.findById(id);
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAddress(Address address) {
        final Address save = addressRepository.save(address);
        return Response.status(Response.Status.CREATED)
                .entity(save)
                .build();

    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{oid}/name")
    public String getName(@PathParam("oid") Long id) {

        return addressRepository.findById(id).getName();
    }
}
