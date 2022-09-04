package com.epam.library.books;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.random.RandomGenerator;

@Component
class BookId {

    static Set<Long> id = new HashSet<>();

    static Long createBookCookieId(){
        RandomGenerator aDefault = RandomGenerator.getDefault();
        long randomLong = aDefault.nextLong();
        if (!id.contains(randomLong)){
            id.add(randomLong);
            return randomLong;
        }
        return createBookCookieId();
    }
}
