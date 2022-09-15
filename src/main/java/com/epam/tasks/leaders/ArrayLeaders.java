package com.epam.tasks.leaders;

import java.util.ArrayList;
import java.util.List;

public class ArrayLeaders {

    public List arrayLeaders(int[] numbers) {

        List<Integer> list = new ArrayList<>();
        for (int number : numbers) {
            list.add(number);
        }

        List<Integer> result = new ArrayList<>();
        if (list.get(list.size()-1)>0){
            result.add(list.get(list.size()-1));
        }

        for (int i = list.size() - 2; i >= 0; i--) {
            if (list.get(i) > list.subList(i+1, list.size()).stream().reduce(Integer::sum).get()) {
                result.add(list.get(i));
            }
        }
        return result.stream().sorted((x,y)->-1).toList();
    }
}
