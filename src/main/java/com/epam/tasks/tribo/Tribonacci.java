package com.epam.tasks.tribo;

import java.util.ArrayList;
import java.util.List;

public class Tribonacci {

    public double[] tribonacci(double[] s, int n) {
        if (s.length<1) return new double[]{};

        List<Double> list = new ArrayList<>();

        for(int i = 0; i<3; i++){
            list.add(s[i]);
        }

        int i = 3;

        while(i<n){
            list.add(list.stream().sorted((x,y)->-1).limit(3).mapToDouble(x->x).reduce(Double::sum).getAsDouble());
            i++;
        }

        double[] result = new double[list.size()];
        for (int i1 = 0; i1 < result.length; i1++) {
            result[i1] = list.get(i1);
        }
        return result;
    }
}
