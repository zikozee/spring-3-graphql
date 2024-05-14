package com.gql.graghql.datasource.fake;

import com.gql.graghql.codegen.types.Address;
import com.gql.graghql.codegen.types.Author;
import com.gql.graghql.codegen.types.Book;
import com.gql.graghql.codegen.types.ReleaseHistory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 09 May, 2024
 */

@Configuration
@RequiredArgsConstructor
public class FakeBookDatasource {

    private final Faker faker;

    public static final List<Book> BOOK_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct(){
        for (int i = 0; i < 20; i++) {
            List<Address> addressList = new ArrayList<>();

            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 3); j++) {
                Address address = Address.newBuilder()
                        .country(faker.address().country())
                        .city(faker.address().city())
                        .street(faker.address().streetAddress())
                        .zipCode(faker.address().zipCode())
                        .build();

                addressList.add(address);
            }

            Author author = Author.newBuilder()
                    .name(faker.book().author())
                    .originCountry(faker.country().name())
                    .addresses(addressList)
                    .build();

            ReleaseHistory released = ReleaseHistory.newBuilder()
                    .printedEdition(faker.bool().bool())
                    .releasedCountry(faker.country().name())
                    .year(faker.number().numberBetween(2019, 2024))
                    .build();

            Book book = Book.newBuilder()
                    .publisher(faker.book().publisher())
                    .title(faker.book().title())
                    .author(author)
                    .released(released)
                    .build();

            BOOK_LIST.add(book);
        }
    }
}
