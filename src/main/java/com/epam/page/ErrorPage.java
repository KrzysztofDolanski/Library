package com.epam.page;

import java.net.URI;

import static java.util.Objects.requireNonNull;

public record ErrorPage(URI url, Exception ex) implements Page{

    public ErrorPage {
        requireNonNull(url);
        requireNonNull(ex);
    }
}
