package com.epam.library.books.delivery.addresses;

import java.util.Set;

public enum AddressesAllowed {

    GDANSK(Set.of(Gdansk.values())),
    SOPOT(Set.of(Sopot.values()));

    Set<Enum<?>> streets;

    AddressesAllowed(Set<Enum<?>> streets) {
        this.streets = streets;
    }
}
