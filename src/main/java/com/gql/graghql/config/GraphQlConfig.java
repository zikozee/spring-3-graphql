package com.gql.graghql.config;


import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 May, 2024
 */

@Configuration
public class GraphQlConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
//                .scalar(ExtendedScalars.Url)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.NonNegativeInt);
    }

}
