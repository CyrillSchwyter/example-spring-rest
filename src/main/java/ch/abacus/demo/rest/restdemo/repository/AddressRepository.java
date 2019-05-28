package ch.abacus.demo.rest.restdemo.repository;

import ch.abacus.demo.rest.restdemo.model.Address;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AddressRepository {

    private Map<Long, Address> addresses;

    public AddressRepository() {
        addresses = new HashMap<>();
        fillWithTestData();
    }

    public Collection<Address> getAllAddresses() {

        return addresses.values();

    }

    private void fillWithTestData() {

        Address address = new Address();
        address.setSurname("Hans");
        address.setName("Muster");
        address.setCity("Wittenbach");
        address.setZip("9300");

        save(address);

    }


    public Long save(Address address) {

        boolean exits = addresses.containsKey(address.getId());

        if (exits) {
            addresses.put(address.getId(), address);

        } else {
            Long newId = Integer.toUnsignedLong(addresses.keySet().size());
            address.setId(newId);
            addresses.put(newId, address);
        }
        return address.getId();
    }
}
