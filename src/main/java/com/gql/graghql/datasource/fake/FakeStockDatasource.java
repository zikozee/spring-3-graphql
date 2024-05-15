package com.gql.graghql.datasource.fake;

import com.gql.graghql.codegen.types.Stock;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 15 May, 2024
 */

@Configuration
@RequiredArgsConstructor
public class FakeStockDatasource {

    private final Faker faker;

    public Stock randomStock(){
        return Stock.newBuilder()
                .symbol(faker.stock().nyseSymbol())
                .price(faker.random().nextInt(100, 1000))
                .lastTradeDateTime(LocalDateTime.now())
                .build();
    }
}
