package com.gql.graghql.datasource.fake;

import com.gql.graghql.codegen.types.Hello;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 26 Apr, 2024
 */

@Configuration
@RequiredArgsConstructor
public class FakeHelloDatasource {

    private final Faker faker;


    public static final List<Hello> HELLO_LIST = new ArrayList<>();

    @PostConstruct
    private void init(){
        for (int i = 0; i < 20; i++){
            Hello hello = Hello.newBuilder()
                    .randomNumber(faker.random().nextInt(5000))
                    .text(faker.company().name())
                    .build();

            HELLO_LIST.add(hello);
        }
    }

}
