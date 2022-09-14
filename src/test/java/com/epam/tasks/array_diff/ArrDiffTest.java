package com.epam.tasks.array_diff;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


class ArrDiffTest {


    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int [] {1,2}, new int[] {1}, new int[] {2}),
                Arguments.of(new int [] {1,2,2}, new int[] {1}, new int[] {2,2}),
                Arguments.of(new int [] {1,2,2}, new int[] {2}, new int[] {1}),
                Arguments.of(new int [] {1,2,2}, new int[] {}, new int[] {1,2,2}),
                Arguments.of(new int [] {}, new int[] {1,2}, new int[] {})
        );
    }

    @ParameterizedTest(name = "{index} => arr1={0}, arr2={1}, result={2}")
    @MethodSource("arguments")
    public void shouldRemoveAllOccurrenceOfArrayA(int[] arr1, int[] arr2, int[] result) {
        //given
        ArrDiff arrDiff = new ArrDiff();
        //when
        int[] arrayDiff = arrDiff.arrayDiff(arr1, arr2);
        //then
        assertTrue(Arrays.equals(result, arrayDiff));
    }

}