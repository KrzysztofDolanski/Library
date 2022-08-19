package com.epam.library.database;

//import org.flywaydb.core.Flyway;

public final class FlywayConnector {

    private static volatile FlywayConnector instance;

    private String url;
    private String user;
    private String password;

    private FlywayConnector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Method to get instance of singleton FlywayConnector.
     * @param url url of database
     * @param username name of database user
     * @param password password of database
     * @return instance of FlywayConnector
     */
    public static FlywayConnector instance(String url, String username, String password) {
        FlywayConnector result = instance;

        if (result != null) {
            return result;
        }
        synchronized (FlywayConnector.class) {
            if (instance == null) {
                instance = new FlywayConnector(url, username, password);
            }
        }
        return instance;
    }

    /**
     * This method invoke Flyway with resources from instance of "singleton" FlywayConnector.
     */
    public static void runUpFlyway() {
//        Flyway flyway = Flyway.configure().dataSource(instance.url, instance.user, instance.password).load();
//        flyway.migrate();
    }
}
