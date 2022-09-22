package com.epam.library.delivery.email;

import com.epam.library.delivery.Address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeliverByEmail extends Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String email;

    String TYPE = "DeliverByEmail";
    String absolute_address;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAbsolute_address() {
        return absolute_address;
    }

    public void setAbsolute_address() {
        this.absolute_address = this.email;
    }
}
