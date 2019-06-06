package ch.abacus.demo.rest.restdemo.repository;

import ch.abacus.demo.rest.restdemo.model.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AddressRepository {

    private Map<Long, Address> addresses;

    public AddressRepository() {
        addresses = new HashMap<>();
        fillWithTestData();
    }

    public boolean exist(Long id){
        return addresses.containsKey(id);
    }

    public Collection<Address> getAllAddresses(String filterName,
                                               String filterSurname,
                                               String filterZip,
                                               String filterCity) {
        List<Address> addresses = new ArrayList<>();
        addresses.addAll(this.addresses.values());
        if(filterName!=null){
            addresses.removeIf(address -> !address.getName().equals(filterName));
        }
        if(filterSurname!=null){
            addresses.removeIf(address -> !address.getSurname().equals(filterSurname));
        }
        if(filterZip!=null){
            addresses.removeIf(address -> !address.getZip().equals(filterZip));
        }
        if(filterCity!=null){
            addresses.removeIf(address -> !address.getZip().equals(filterCity));
        }
        return new ArrayList<>(addresses);
    }

    public Address findById(Long id) {
        return addresses.get(id);
    }

    public Address delete(Long id) {
        return addresses.remove(id);
    }

    public Address save(Address address) {
        if (exist(address.getId())) {
            addresses.put(address.getId(), address);

        } else {
            Long newId = Integer.toUnsignedLong(addresses.keySet().size());
            address.setId(newId);
            addresses.put(newId, address);
        }
        return address;
    }

    private void fillWithTestData() {
        addAddress("Hans","Mueller",9300,"Wittenbach");
        addAddress("Haidi","Mueller",9300,"Wittenbach");
        addAddress("Fritz","Bauer",9300,"Wittenbach");
        addAddress("Max","Meier",9000,"St. Gallen");

    }

    private void addAddress(String surname, String name, int zip, String city){
        Address address = new Address();
        address.setSurname(surname);
        address.setName(name);
        address.setZip(String.valueOf(zip));
        address.setCity(city);

        save(address);
    }
}
