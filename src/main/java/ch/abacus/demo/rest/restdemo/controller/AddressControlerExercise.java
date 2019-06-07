package ch.abacus.demo.rest.restdemo.controller;

import ch.abacus.demo.rest.restdemo.model.Address;
import ch.abacus.demo.rest.restdemo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Component
@Path("/addresses")
public class AddressControlerExercise {


    private final AddressRepository addressRepository;

    public AddressControlerExercise(@Autowired AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/test")
    public String sayHello() {
        // HTTP-Statuscode 200 OK
        return "Hello du";
    }


    //todo Annotation HTTP Methode
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Address> getAddresses(/*todo Zusatzaufgabe: Annotation Parameter zum filtern*/) {
        // HTTP-Statuscode 200 OK
        return addressRepository.getAllAddresses(null, null, null, null);
    }


    //todo Annotation HTTP Methode
    //todo Annotation URL Pfad
    @Produces(MediaType.APPLICATION_JSON)
    public Address getAddress(/*todo Annotation Parameter*/ Long id) {
        // HTTP-Statuscode 200 OK  oder
        // HTTP-Statuscode 204 NO_CONTENT
        return addressRepository.findById(id);
        //todo Zusatzaufgabe HTTP 404 falls die Adresse mit der id nicht gefunden wurde
    }


    //todo Annotation HTTP Methode
    //todo Annotation URL Pfad
    @Produces(MediaType.TEXT_PLAIN)
    public String getName(/*todo Annotation Parameter*/ Long id) {
        // HTTP-Statuscode 200 OK  oder
        // HTTP-Statuscode 500 INTERNAL_SERVER_ERROR
        return addressRepository.findById(id).getName();
        //todo Zusatzaufgabe: HTTP 404 falls die Adresse mit der id nicht gefunden wurde
    }


    //todo Annotation HTTP Methode
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveAddress(Address address) {
        if(addressRepository.exist(address.getId())){
            // HTTP-Statuscode 405 METHOD_NOT_ALLOWED
            // Die Anfrage darf nur mit anderen HTTP-Methoden (zum Beispiel GET statt POST) gestellt werden.
            // Gültige Methoden für die betreffende Ressource werden im „Allow“-Header-Feld der Antwort übermittelt.
            return  null;// todo Response 405 METHOD_NOT_ALLOWED
        }
        else {
            // HTTP-Statuscode 201 CREATED
            // Die Anfrage wurde erfolgreich bearbeitet.
            // Die angeforderte Ressource wurde vor dem Senden der Antwort erstellt.
            return  null;// todo Response 201 CREATED mit der gespeicherten Adresse
        }
    }


    //todo Annotation HTTP Methode
    //todo Annotation URL Pfad
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(/*todo Annotation Parameter*/ Long id, Address address) {
        // todo Zusatzaufgabe: Response 404 NOT_FOUND wenn keine Adresse mit der id existiert
        // todo Zusatzaufgabe: Response 409 CONFLICT wenn die id aus der URL nicht gleich ist wie die id der Adresse
       final Address save = addressRepository.save(address);
       return null; // todo Response 200 OK mit der gespeicherten Adresse
    }

    //todo Annotation HTTP Methode
    //todo Annotation URL Pfad
    @Produces(MediaType.APPLICATION_JSON)
    public Address deleteAddress(/*todo Annotation Parameter*/ Long id){
        // HTTP-Statuscode 200 OK  oder
        // HTTP-Statuscode 204 NO_CONTENT
        return addressRepository.delete(id);
    }
}
