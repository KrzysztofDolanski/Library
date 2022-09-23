package com.epam.tasks.tribo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TribonacciTest {

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new double []{1,1,1},10, new double []{1,1,1,3,5,9,17,31,57,105}),
                Arguments.of(new double []{0,0,1},10, new double []{0,0,1,1,2,4,7,13,24,44}),
                Arguments.of(new double []{0,1,1},10, new double []{0,1,1,2,4,7,13,24,44,81})
        );
    }

    @ParameterizedTest(name = "{index} => arr={0}, value={1}, expected={2}")
    @MethodSource("arguments")
    public void shouldReturnExpectedArray(double[] arr, int value, double[] expected){
      //given
        Tribonacci tribonacci = new Tribonacci();
        //when
        double[] result = tribonacci.tribonacci(arr, value);
        //then
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(result));
        assertArrayEquals(expected, result);
    }

}