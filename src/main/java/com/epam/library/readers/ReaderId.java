package com.epam.library.readers;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.random.RandomGenerator;

@Component
public class ReaderId {

    static Set<Long> id = new HashSet<>();

    static Long createReaderCookieId(){
        RandomGenerator aDefault = RandomGenerator.getDefault();
        long randomLong = aDefault.nextLong();
        if (!id.contains(randomLong)){
            id.add(randomLong);
            return randomLong;
        }
        return createReaderCookieId();
    }
}
