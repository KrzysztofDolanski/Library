package com.epam.library.delivery.post;

import com.epam.library.delivery.Delivery;
import com.epam.library.delivery.addresses.AddressesAllowed;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class DeliverByPostService implements Delivery {

    public boolean addressAccessibility(String city, String streetName, Integer homeNumber, Integer flatNumber) {
        return Arrays
                .stream(AddressesAllowed.values())
                .filter(c -> c.name().equalsIgnoreCase(city))
                .toList().get(0).streets
                .stream()
                .toList()
                .stream()
                .filter(x -> x.toString().equalsIgnoreCase(streetName))
                .findFirst().orElseThrow()
                .getNumbersFlats()
                .containsKey(homeNumber)
                ?
                true : false;
    }

    @Override
    public boolean deliveryProcedure(String data) {

        return false;
    }

    @Override
    public void collectDeliverDetails() {

    }
}
