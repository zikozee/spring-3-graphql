package com.gql.graghql.controller;

import com.gql.graghql.codegen.DgsConstants;
import com.gql.graghql.codegen.types.Hello;
import com.gql.graghql.codegen.types.HelloInput;
import com.gql.graghql.datasource.fake.FakeHelloDatasource;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

//    @MutationMapping(name = DgsConstants.MUTATION.AddHello)
    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.AddHello
    )
    public int createHello(@Argument(name = "helloInput") HelloInput input){
        FakeHelloDatasource.HELLO_LIST.add(
                Hello.newBuilder()
                        .text(input.getText())
                        .randomNumber(input.getNumber()).build()
        );
        return FakeHelloDatasource.HELLO_LIST.size();
    }

    @MutationMapping(name = DgsConstants.MUTATION.ReplaceHelloText)
    public List<Hello> updateHello(@Argument(name = "helloInput") HelloInput input){

        FakeHelloDatasource.HELLO_LIST.stream()
                .filter(hello -> hello.getRandomNumber().equals(input.getNumber()))
                .forEach(hello -> hello.setText(input.getText()));

        return FakeHelloDatasource.HELLO_LIST;
    }

    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.DeleteHello
    )
    public int deleteHello(@Argument(name = "number") int number){

        FakeHelloDatasource.HELLO_LIST.removeIf(hello -> hello.getRandomNumber() == (number));

        return FakeHelloDatasource.HELLO_LIST.size();
    }
}
