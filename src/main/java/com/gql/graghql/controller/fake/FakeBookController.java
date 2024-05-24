//package com.gql.graghql.controller.fake;
//
//import com.gql.graghql.codegen.DgsConstants;
//import com.gql.graghql.codegen.types.Book;
//import com.gql.graghql.codegen.types.ReleaseHistory;
//import com.gql.graghql.codegen.types.ReleaseHistoryInput;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.graphql.data.method.annotation.SchemaMapping;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//import static com.gql.graghql.datasource.fake.FakeBookDatasource.BOOK_LIST;
//
///**
// * @author : Ezekiel Eromosei
// * @code @created : 09 May, 2024
// */
//
//@Controller
//public class FakeBookController {
//
//    @QueryMapping(name = DgsConstants.QUERY.Books)
////    @SchemaMapping(typeName = "Query", field = "books")
//    public List<Book> booksWrittenBy(@Argument(name = "author") String authorName){
//        if(StringUtils.isBlank(authorName))
//            return BOOK_LIST;
//        return BOOK_LIST
//                .stream()
//                .filter(book -> StringUtils.containsIgnoreCase(book.getAuthor().getName(), authorName))
//                .toList();
//    }
//
//    @SchemaMapping(
//            typeName = DgsConstants.QUERY_TYPE,
//            field =  DgsConstants.QUERY.BooksByReleased
//    )
//    public List<Book> getBooksByReleased(@Argument ReleaseHistoryInput releasedInput) {
//        return BOOK_LIST.stream()
//                .filter(b-> this.matchReleaseHistory(releasedInput, b.getReleased()))
//                .toList();
//    }
//
//    private boolean matchReleaseHistory(ReleaseHistoryInput input, ReleaseHistory element){
//        return input.getPrintedEdition().equals(element.getPrintedEdition())
//                && input.getYear() == element.getYear();
//    }
//
//}
