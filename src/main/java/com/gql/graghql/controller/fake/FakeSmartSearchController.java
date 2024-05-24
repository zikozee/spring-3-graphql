package com.gql.graghql.controller.fake;

import com.gql.graghql.codegen.DgsConstants;
import com.gql.graghql.datasource.fake.FakeBookDatasource;
import com.gql.graghql.datasource.fake.FakeHelloDatasource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 15 May, 2024
 */

@Controller
public class FakeSmartSearchController {

    @SchemaMapping(
            typeName = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.SmartSearch
    )
    public List<?> getSmartSearch(@Argument(name = "keyword") Optional<String> key) {
        List<Object> smartSearchResults = new ArrayList<>();

        if(key.isEmpty()){
            smartSearchResults.addAll(FakeHelloDatasource.HELLO_LIST);
            smartSearchResults.addAll(FakeBookDatasource.BOOK_LIST);
        }else{
            String keywordString = key.get();

            FakeHelloDatasource.HELLO_LIST
                    .stream()
                    .filter(hello -> StringUtils.containsIgnoreCase(hello.getText(), keywordString))
                    .forEach(smartSearchResults::add);

            FakeBookDatasource.BOOK_LIST
                    .stream()
                    .filter(book -> StringUtils.containsIgnoreCase(book.getTitle(), keywordString))
                    .forEach(smartSearchResults::add);
        }

        return smartSearchResults;
    }
}
