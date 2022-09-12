package com.epam.patterns.factory;

public class Main {


    private static Factory factory;

    public static void main(String[] args) {
        configure();
        run();
    }

    private static void configure() {
        if (System.getProperty("os.name").contains("Windows")){
            factory = new WindowDialog();
        } else {
            factory = new HtmlDialog();
        }
    }

    static void run(){
        factory.renderWindow();
    }
}