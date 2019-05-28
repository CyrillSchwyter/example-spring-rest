package ch.abacus.demo.rest.restdemo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address {

    private Long id;
    private String surname;
    private String name;
    private String city;
    private String zip;


    public Address() {
//        Framework benoetigt einen Empty konstrukor
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
