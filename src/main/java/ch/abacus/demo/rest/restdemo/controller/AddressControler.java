package ch.abacus.demo.rest.restdemo.controller;

import ch.abacus.demo.rest.restdemo.model.Address;
import ch.abacus.demo.rest.restdemo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Collections;

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
        // HTTP-Statuscode 200 OK
        return "Hello du";
    }


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Address> getAddresses(@QueryParam("name") String name,
                                            @QueryParam("surname") String surname,
                                            @QueryParam("zip") String zip,
                                            @QueryParam("city") String city) {
        // HTTP-Statuscode 200 OK
        return addressRepository.getAllAddresses(name, surname, zip, city);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getAddress(@PathParam("id") Long id) {
        Address address = addressRepository.findById(id);
        if(address!=null){
            // HTTP-Statuscode 200 OK
            return Response.status(Response.Status.OK).entity(address).build();
        }
        else {
            // HTTP-Statuscode 404 NOT_FOUND
            // Die angeforderte Ressource wurde nicht gefunden.
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

//    // einfache implementierung
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{id}")
//    public Address getAddress(@PathParam("id") Long id) {
//        // HTTP-Statuscode 200 OK  oder
//        // HTTP-Statuscode 204 NO_CONTENT
//        return addressRepository.findById(id);
//    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}/name")
    public String getName(@PathParam("id") Long id) {
        // HTTP-Statuscode 200 OK  oder
        // HTTP-Statuscode 500 INTERNAL_SERVER_ERROR
        return addressRepository.findById(id).getName();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}/surname")
    public Response getSurname(@PathParam("id") Long id) {
        if(addressRepository.exist(id)) {
            // HTTP-Statuscode 200 OK
            String surName = addressRepository.findById(id).getSurname();
            return Response.status(Response.Status.OK).entity(surName).build();
        }
        else {
            // HTTP-Statuscode 404 NOT_FOUND
            // Die angeforderte Ressource wurde nicht gefunden.
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}/zip")
    public String getZip(@PathParam("id") Long id) {
        return addressRepository.findById(id).getZip();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}/city")
    public String getCity(@PathParam("id") Long id) {
        return addressRepository.findById(id).getCity();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAddress(Address address) {
        if(addressRepository.exist(address.getId())){
            // HTTP-Statuscode 405 METHOD_NOT_ALLOWED
            // Die Anfrage darf nur mit anderen HTTP-Methoden (zum Beispiel GET statt POST) gestellt werden.
            // Gültige Methoden für die betreffende Ressource werden im „Allow“-Header-Feld der Antwort übermittelt.
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        }
        else {
            // HTTP-Statuscode 201 CREATED
            // Die Anfrage wurde erfolgreich bearbeitet.
            // Die angeforderte Ressource wurde vor dem Senden der Antwort erstellt.
            final Address save = addressRepository.save(address);
            return Response.status(Response.Status.CREATED)
                    .entity(save)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateAddress(@PathParam("id") Long id, Address address) {
        if ( !addressRepository.exist(id)) {
            // HTTP-Statuscode 404 NOT_FOUND
            // Die angeforderte Ressource wurde nicht gefunden.
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        else if( !id.equals(address.getId()) ){
            // HTTP-Statuscode 409 CONFLICT
            // Die Anfrage wurde unter falschen Annahmen gestellt.
            return Response.status(Response.Status.CONFLICT).build();
        }
        else {
            final Address save = addressRepository.save(address);
            // HTTP-Statuscode 200 OK
            return Response.status(Response.Status.OK)
                    .entity(save)
                    .build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Address deleteAddress(@PathParam("id") Long id){
        // HTTP-Statuscode 200 OK  oder
        // HTTP-Statuscode 204 NO_CONTENT
        return addressRepository.delete(id);
    }
}
