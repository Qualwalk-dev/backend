package com.qualwalk.backend.configuration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
public class DatabaseInitialization {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void initiliazeFlyway() {
        FluentConfiguration configuration = new FluentConfiguration()
                .dataSource(dataSource)
                .sqlMigrationPrefix("V")
                .repeatableSqlMigrationPrefix("R")
                .sqlMigrationSeparator("__")
                .sqlMigrationSuffixes(".sql");
        Flyway flyway = new Flyway(configuration);
        flyway.migrate();
    }
}
