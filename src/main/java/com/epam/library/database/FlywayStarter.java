package com.epam.library.database;

import java.io.IOException;
import java.util.Properties;

public class FlywayStarter {

    public void start() throws IOException {
        var props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("/application.properties"));

        FlywayConnector.instance(props.getProperty("db.url"),
                props.getProperty("db.username"),
                props.getProperty("db.password"));

        FlywayConnector.runUpFlyway();
    }
}
