package com.epam.library.readers;

import java.lang.module.FindException;

public class ReaderNotFoundException extends FindException {

    @Override
    public String getMessage() {
        return "Reader not found";
    }

}
