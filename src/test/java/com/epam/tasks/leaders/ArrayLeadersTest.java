package com.epam.tasks.leaders;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayLeadersTest {



    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new int[]{1,2,3,4,0}, Arrays.asList(4)),
                Arguments.of(new int[]{16,17,4,3,5,2}, Arrays.asList(17,5,2)),
                Arguments.of(new int[]{-1,-29,-26,-2}, Arrays.asList(-1)),
                Arguments.of(new int[]{-36,-12,-27}, Arrays.asList(-36,-12)),
                Arguments.of(new int[]{5,2}, Arrays.asList(5,2)),
                Arguments.of(new int[]{0,-1,-29,3,2}, Arrays.asList(0,-1,3,2))
        );
    }


    @ParameterizedTest(name = "{index} => arr={0}, result={1}")
    @MethodSource("arguments")
    public void shouldReturnExpectedList(int[] arr, List result) {
        //given
        ArrayLeaders arrayLeaders = new ArrayLeaders();
        //when
        List list = arrayLeaders.arrayLeaders(arr);
        //then
        assertEquals(result, list);
    }

}