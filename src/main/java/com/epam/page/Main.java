package com.epam.page;

import java.net.URI;
import java.net.http.HttpClient;

public class Main {

    public static void main(String[] args)  throws Exception{
        var uri = new URI("https://github.com/junit-pioneer/junit-pioneer/pull/627");

        var client = HttpClient.newHttpClient();
        PageTreeFactory pageTreeFactory = new PageTreeFactory(client);
//        System.out.println(pageTreeFactory.fetchPageAsString(uri));
    }
}
