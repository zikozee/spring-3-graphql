package com.gql.graghql.datasource.fake;

import com.gql.graghql.codegen.types.Cat;
import com.gql.graghql.codegen.types.Dog;
import com.gql.graghql.codegen.types.Pet;
import com.gql.graghql.codegen.types.PetFoodType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 15 May, 2024
 */

@Configuration
@RequiredArgsConstructor
public class FakePetDatasource {

    public static final List<Pet> PET_LIST = new ArrayList<>();


    private final Faker faker;

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 10; i++) {
            Pet animal = switch (i % 2){
                case 0 -> Dog.newBuilder().name(faker.dog().name())
                        .food(PetFoodType.OMNIVORE)
                        .breed(faker.dog().breed())
                        .size(faker.dog().size())
                        .coatLength(faker.dog().coatLength())
                        .build();
                default -> Cat.newBuilder().name(faker.cat().name())
                        .food(PetFoodType.CARNIVORE)
                        .breed(faker.cat().breed())
                        .registry(faker.cat().registry())
                        .build();
            };

            PET_LIST.add(animal);
        }
    }
}
