package com.epam.tasks.road;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadHousesNumberTest {


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(1, 3, 6),
                Arguments.of(3, 3, 4),
                Arguments.of(2, 3, 5),
                Arguments.of(3, 5, 8),
                Arguments.of(7, 11, 16),
                Arguments.of(20, 1000000, 1999981L),
                Arguments.of( 23633656673L, 310027696726L, 596421736780L)
        );
    }

    @ParameterizedTest(name = "{index} => address={0}, size={1}, result={2}")
    @MethodSource("arguments")
    public void shouldReturnGivenNumber(long address, long size, long result) {

        RoadHousesNumber roadHousesNumber = new RoadHousesNumber();
        assertEquals(result, roadHousesNumber.overTheRoad(address, size));
    }

}