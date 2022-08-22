package com.epam.library;

import com.epam.library.database.StatementExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

        StatementExecutor stm = new StatementExecutor();
        stm.writeStatementFromTerminal();
    }



}
