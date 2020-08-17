package ru.makarov;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * MAIN class.
 */
@SpringBootApplication
public class AuthAppClient {

    public static void main(String[] args) {
        SpringApplication.run(AuthAppClient.class, args);
    }
}
