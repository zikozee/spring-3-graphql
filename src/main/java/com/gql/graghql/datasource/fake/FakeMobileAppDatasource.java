package com.gql.graghql.datasource.fake;

import com.gql.graghql.codegen.types.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 14 May, 2024
 */

@Configuration
@RequiredArgsConstructor
public class FakeMobileAppDatasource {

    private final Faker faker;

    public static final List<MobileApp> MOBILE_APP_LIST = new ArrayList<>();

    @PostConstruct
    private void postConstruct() throws MalformedURLException {
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


            MobileApp mobileApp = MobileApp.newBuilder()
                    .name(faker.app().name())
                    .author(author)
                    .version(faker.app().version())
                    .platform(randomMobileAppPlatform())
                    .appId(UUID.randomUUID().toString())
                    .releaseDate(LocalDate.now().minusDays(faker.random().nextInt(365)))
                    .downloaded(faker.number().numberBetween(1, 1_500_000))
                    .homepage("https://" + faker.internet().url())
                    .build();

            MOBILE_APP_LIST.add(mobileApp);
        }
    }

    private List<String> randomMobileAppPlatform(){
        return switch (ThreadLocalRandom.current().nextInt(10) % 3) {
            case 0 -> List.of("android");
            case 1 -> List.of("ios");
            default -> List.of("ios", "android");
        };
    }
}
