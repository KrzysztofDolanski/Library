package com.epam.library;

import com.epam.library.database.StatementExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

@SpringBootApplication
public class LibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
        int tries = 6;
        StatementExecutor stm = new StatementExecutor();
        Scanner sc = new Scanner(System.in);
        while (tries>0){
            String statement = sc.nextLine();
            try {
                String execute = stm.execute(statement);
                System.err.println(execute);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
            tries--;
        }
        sc.close();
    }

}
