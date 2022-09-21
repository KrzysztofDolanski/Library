package com.epam.library.delivery.post;

import com.epam.library.delivery.Address;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeliverByPost extends Address {

    @Id
    Long id;
    String city;
    String street_name;
    Integer home_number;
    Integer flat_number;

    String TYPE = "DeliverByPost";
    String absolute_address;

    public DeliverByPost(String city, String street_name, Integer home_number, Integer flat_number) {
        this.city = city;
        this.street_name = street_name;
        this.home_number = home_number;
        this.flat_number = flat_number;
    }

    public DeliverByPost() {
    }

    public String getAbsoluteAddress() {
        return absolute_address = this.city + " " + this.street_name + " " + this.home_number + " " + this.flat_number;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public Integer getHome_number() {
        return home_number;
    }

    public void setHome_number(Integer home_number) {
        this.home_number = home_number;
    }

    public Integer getFlat_number() {
        return flat_number;
    }

    public void setFlat_number(Integer flat_number) {
        this.flat_number = flat_number;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getAbsolute_address() {
        return absolute_address;
    }

    public void setAbsolute_address(String absolute_address) {
        this.absolute_address = absolute_address;
    }
}
