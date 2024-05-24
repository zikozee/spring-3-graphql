package com.gql.graghql.controller.problemz;

import com.gql.graghql.codegen.DgsConstants;
import com.gql.graghql.codegen.types.Problem;
import com.gql.graghql.codegen.types.SearchItemFilter;
import com.gql.graghql.codegen.types.SearchableItem;
import com.gql.graghql.codegen.types.Solution;
import com.gql.graghql.exception.NotFoundException;
import com.gql.graghql.service.query.ProblemzQueryService;
import com.gql.graghql.service.query.SolutionzQueryService;
import com.gql.graghql.util.GraphqlBeanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Controller
@RequiredArgsConstructor
public class ItemSearchController {
    private final ProblemzQueryService problemzQueryService;
    private final SolutionzQueryService solutionzQueryService;

    @SchemaMapping(
            typeName = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.ItemSearch
    )
    public List<SearchableItem> searchableItems(@Argument(name = "filter") SearchItemFilter filter) {
        String keyword = filter.getKeyword();

        List<SearchableItem> result = new ArrayList<>();

        List<Problem> problemzByKeyword = problemzQueryService.problemByKeyword(keyword)
                .stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList();

        List<Solution> solutionzByKeyword = solutionzQueryService.problemByKeyword(keyword)
                .stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList();

        result.addAll(problemzByKeyword);
        result.addAll(solutionzByKeyword);

        if(result.isEmpty()) throw new NotFoundException("No Item with keyword "+ keyword);

        result.sort(Comparator.comparing(SearchableItem::getCreatedDateTime).reversed());
        return result;
    }
}
