package com.epam.library.delivery.addresses;

import java.util.Set;

public enum AddressesAllowed {

    GDANSK(Set.of(Gdansk.values())),
    SOPOT(Set.of(Sopot.values()));

    public Set<City> streets;

    AddressesAllowed(Set<City> streets) {
        this.streets = streets;
    }
}
