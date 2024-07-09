package org.toni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * The HelloWorldApplication class implements an application that
 * simply starts the Spring Boot application.
 */
@SpringBootApplication
public class HelloWorldApplication {
    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldApplication.class, args);
    }

    @PostConstruct
    public void printDatasourceConfig() {
        try {
            System.out.println("DB URL: " + dataSource.getConnection().getMetaData().getURL());
            System.out.println("DB Username: " + dataSource.getConnection().getMetaData().getUserName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}