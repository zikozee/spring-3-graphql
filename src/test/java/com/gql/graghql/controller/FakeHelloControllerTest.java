package com.gql.graghql.controller;

import com.gql.graghql.codegen.types.Hello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

//to Load everything
@SpringBootTest
@AutoConfigureGraphQlTester

//load slice
//@GraphQlTest(FakeHelloController.class)
class FakeHelloControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void allHellosTest() {
        this.graphQlTester
                .documentName("allHellosTest")
                .execute()
                .path("allHellos")
                .entityList(Hello.class).hasSize(20);
    }

    @Test
    void oneHelloTest() {
        this.graphQlTester
                .documentName("oneHelloTest")
                .execute()
                .path("oneHello")
                .hasValue()
                .matchesJson("""
                        {
                            "text": "Schroeder Inc",
                            "randomNumber": 1586
                        }
                        """);
    }
}