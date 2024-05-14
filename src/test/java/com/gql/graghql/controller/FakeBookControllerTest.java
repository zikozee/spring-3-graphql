package com.gql.graghql.controller;

import com.gql.graghql.codegen.types.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureGraphQlTester

//load slice
//@GraphQlTest(FakeBookController.class)
class FakeBookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void booksWrittenBy() {


        this.graphQlTester
                .documentName("booksWrittenByTest")
                .execute()
                .path("books")
                .hasValue()
                .entityList(Book.class)
                .hasSize(20);
    }

    @Test
    void getBooksByReleasedWithoutInput() {
        this.graphQlTester
                .documentName("getBooksByReleasedTest")
                .variable("releasePrintedEdition", true)
                .execute()
                .path("booksByReleased[*].released.year")
                .entityList(Integer.class).doesNotContain(2005);
    }

    @Test
    void getBooksByReleased() {
        this.graphQlTester
                .documentName("getBooksByReleasedTest")
                .variable("releasePrintedEdition", true)
                .variable("releaseYear", 2019)
                .execute()
                .path("booksByReleased[*].released.year")
                .entityList(Integer.class).contains(2019);
    }



    @Test
    void testYear() {
        this.graphQlTester
                .documentName("booksWrittenByTest")
                .execute()
                .path("books[*].released.year")
                .hasValue()
                .entityList(Integer.class)
                .doesNotContain(2020, 2025);
    }

    @Test
    void testTitle() {
        this.graphQlTester
                .documentName("booksWrittenByTest")
                .execute()
                .path("books[*].title")
                .hasValue()
                .entityList(String.class)
                .hasSize(20);
    }
}