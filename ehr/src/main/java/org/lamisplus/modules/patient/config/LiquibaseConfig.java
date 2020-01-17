package org.lamisplus.modules.patient.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

@Configuration
public class LiquibaseConfig {

    private final DataSource dataSource;
    private final ResourceLoader resourceLoader;

    public LiquibaseConfig(DataSource dataSource, ResourceLoader resourceLoader) {
        this.dataSource = dataSource;
        this.resourceLoader = resourceLoader;
    }

//    @Bean
//    public SpringLiquibase liquibase() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setChangeLog("classpath:installers/patient/schema/initial_schema.xml");
//        liquibase.setDataSource(dataSource);
//        return liquibase;
//    }
    @Bean
    public SpringLiquibase liquibase() {
        String changelogFile = "classpath:installers/patient/schema/initial_schema.xml";
        Resource resource = resourceLoader.getResource(changelogFile);
        Assert.state(resource.exists(), "Unable to find file: " + changelogFile);
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(changelogFile);
        liquibase.setDataSource(dataSource);
        liquibase.setDropFirst(false);
        liquibase.setShouldRun(true);
        return liquibase;
    }
}
