//package com.gql.graghql.controller.fake;
//
//import com.gql.graghql.codegen.DgsConstants;
//import com.gql.graghql.codegen.types.Cat;
//import com.gql.graghql.codegen.types.Dog;
//import com.gql.graghql.codegen.types.Pet;
//import com.gql.graghql.codegen.types.PetFilter;
//import com.gql.graghql.datasource.fake.FakePetDatasource;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.SchemaMapping;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author : Ezekiel Eromosei
// * @code @created : 15 May, 2024
// */
//
//@Controller
//public class FakePetController {
//
//    @SchemaMapping(
//            typeName = DgsConstants.QUERY_TYPE,
//            field = DgsConstants.QUERY.Pets
//    )
//    public List<Pet> getPets(@Argument Optional<PetFilter> petFilter) {
//        return petFilter.map(filter -> FakePetDatasource.PET_LIST
//                .stream()
//                .filter(pet -> this.matchFilter(filter, pet))
//                .toList()).orElse(FakePetDatasource.PET_LIST);
//    }
//
//    private boolean matchFilter(PetFilter filter, Pet pet) {
//        if (StringUtils.isBlank(filter.getPetType())) {
//            return true;
//        }
//
//        if (filter.getPetType().equalsIgnoreCase(Dog.class.getSimpleName())) {
//            return pet instanceof Dog;
//        } else if (filter.getPetType().equalsIgnoreCase(Cat.class.getSimpleName())) {
//            return pet instanceof Cat;
//        } else {
//            return false;
//        }
//    }
//}
