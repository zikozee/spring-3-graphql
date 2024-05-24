//package com.gql.graghql.controller.fake;
//
//import com.gql.graghql.codegen.DgsConstants;
//import com.gql.graghql.codegen.types.Stock;
//import com.gql.graghql.datasource.fake.FakeStockDatasource;
//import lombok.RequiredArgsConstructor;
//import org.springframework.graphql.data.method.annotation.SchemaMapping;
//import org.springframework.stereotype.Controller;
//import reactor.core.publisher.Flux;
//
//import java.time.Duration;
//
///**
// * @author : Ezekiel Eromosei
// * @code @created : 15 May, 2024
// */
//
//@Controller
//@RequiredArgsConstructor
//public class FakeStockController {
//
//    private final FakeStockDatasource fakeStockDatasource;
//
////    @SubscriptionMapping
//    @SchemaMapping(
//            typeName = DgsConstants.SUBSCRIPTION_TYPE,
//            field = DgsConstants.SUBSCRIPTION.RandomStock
//    )
//    public Flux<Stock> randomStock(){
//        return Flux.interval(Duration.ofSeconds(3))
//                .map(t-> fakeStockDatasource.randomStock());
//    }
//}
