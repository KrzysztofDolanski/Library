package com.epam.library.delivery.addresses;

import java.util.List;
import java.util.Map;

public enum Sopot implements City{

    ZWYCIESTWA(Map.ofEntries(
            Map.entry(1, List.of(1, 2, 3, 4, 5, 6, 8)),
            Map.entry(2, List.of(10, 11, 12))

    )),
    PADEREWSKIEGO(
            Map.ofEntries(
                    Map.entry(1, List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)),
                    Map.entry(2, List.of(10, 11, 12))
            )
    );

    Map<Integer, List<Integer>> numbersFats;

    Sopot(Map<Integer, List<Integer>> numbersFats) {
        this.numbersFats = numbersFats;
    }

    @Override
    public Map<Integer, List<Integer>> getNumbersFlats() {
        return this.numbersFats;
    }
}
