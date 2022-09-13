package com.epam.tasks.triplet;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;


class TripletSumTest {


    @Parameterized.Parameters
    public static Collection triples() {
        return Arrays.asList(new Object[][] {
                {new int[]{1,2,3}, 12}
        });
    }

    private static Stream<Arguments> arguments() {
        return Stream.of(
          Arguments.of(new int[]{1,2,3}, 6),
                Arguments.of(new int[]{-3,-27,-4,-2,-27,-2}, -9),
                Arguments.of(new int[]{-14,-12,-7,-42,-809,-14,-12}, -33)
        );
    }


    @ParameterizedTest(name = "{index} => numbers={0}, result={1}")
    @MethodSource("arguments")
    void shouldReturnTripletOfSumOfValuesWithoutDuplication(int[] numbers, int result){
        //given
        TripletSum tripletSum = new TripletSum();
        //when
        assertEquals(result,tripletSum.maxTriSum(numbers));
        //then
    }
}