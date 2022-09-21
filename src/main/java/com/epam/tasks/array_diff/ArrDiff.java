package com.epam.tasks.array_diff;

import java.util.*;

public class ArrDiff {

    public int[] arrayDiff(int[] a, int[] b) {

        List<Integer> listA = new LinkedList<>(Arrays.stream(a).boxed().toList());

        for (Integer integer : b) {
            listA.removeAll(List.of(integer));
        }

        return listA.stream().mapToInt(x -> x).toArray();

    }


}
