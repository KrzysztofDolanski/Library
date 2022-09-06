package com.epam.library.readers;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.random.RandomGenerator;

@Component
class ReaderId {

    //todo implement security authentication token
    static Map<Long, Long> id = new HashMap<>();

    static Long createReaderCookieId(Reader reader) {
        RandomGenerator aDefault = RandomGenerator.getDefault();
        long randomLong = aDefault.nextLong();
        if (!id.containsKey(reader.getId())) {
            id.put(reader.getId(), randomLong);
            return randomLong;
        }
        return id.get(reader.getId());
    }
}
