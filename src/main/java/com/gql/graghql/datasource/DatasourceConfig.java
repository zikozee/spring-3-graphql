package com.gql.graghql.datasource;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 26 Apr, 2024
 */

@Configuration
public class DatasourceConfig {

    @Bean
    public Faker faker(){
        return new Faker();
    }
}
