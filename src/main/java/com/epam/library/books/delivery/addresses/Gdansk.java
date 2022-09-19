package com.epam.library.books.delivery.addresses;

import java.util.List;
import java.util.Map;

public enum Gdansk {


    WIECKOWSKIEGO(
            Map.ofEntries(
                    Map.entry(1, List.of(1, 2, 3, 4, 5, 6, 7, 8)),
                    Map.entry(2, List.of(11, 12, 13))
            )
    ), SUCHANKA(
            Map.ofEntries(
                    Map.entry(1, List.of(1, 2, 3, 4, 5, 6, 7, 8)),
                    Map.entry(2, List.of(11, 12, 13))
            )
    );

    Map<Integer, List<Integer>> numbersFlats;

    Gdansk(Map<Integer, List<Integer>> numbersFlats) {
        this.numbersFlats = numbersFlats;
    }
}
