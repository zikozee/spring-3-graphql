package com.gql.graghql.controller;

import com.gql.graghql.codegen.types.Hello;
import com.gql.graghql.datasource.fake.FakeHelloDatasource;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 26 Apr, 2024
 */

@Controller
public class FakeHelloController {


    @QueryMapping
    public List<Hello> allHellos(){
        return FakeHelloDatasource.HELLO_LIST;
    }

    @QueryMapping
    public Hello oneHello(){
        return Hello.newBuilder()
                .text("Schroeder Inc")
                .randomNumber(1586).build();
//        return FakeHelloDatasource.HELLO_LIST
//                .get(ThreadLocalRandom.current().nextInt(FakeHelloDatasource.HELLO_LIST.size()));
    }
}
