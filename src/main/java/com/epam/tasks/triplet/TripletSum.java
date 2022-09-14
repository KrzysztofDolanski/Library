package com.epam.tasks.triplet;

import java.util.Comparator;
import java.util.stream.IntStream;

public class TripletSum {

    public int maxTriSum(int[] numbers) {

        return IntStream.of(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .limit(3)
                .mapToInt(i -> i)
                .sum();
    }

}
