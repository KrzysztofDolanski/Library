package com.epam.tasks.binary;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryAdditionTest {


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of("10", 1, 1),
                Arguments.of("1", 0, 1),
                Arguments.of("1", 1, 0),
                Arguments.of("100", 2, 2),
                Arguments.of("111111", 51, 12)
        );
    }

    @ParameterizedTest(name = "{index} => result={0}, a={1}, b={2}")
    @MethodSource("arguments")
    public void shouldReturnBinaryOfTheSum(String result, int a, int b) {
        //given
        BinaryAddition binaryAddition = new BinaryAddition();
        //when
        String binary = binaryAddition.binaryAddition(a, b);
        //then
        assertEquals(result, binary);
    }


}