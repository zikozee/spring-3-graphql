package com.gql.graghql.controller.problemz;

import com.gql.graghql.codegen.DgsConstants;
import com.gql.graghql.codegen.types.Problem;
import com.gql.graghql.codegen.types.ProblemCreateInput;
import com.gql.graghql.codegen.types.ProblemResponse;
import com.gql.graghql.entity.Problemz;
import com.gql.graghql.entity.Userz;
import com.gql.graghql.exception.AuthenticationException;
import com.gql.graghql.exception.NotFoundException;
import com.gql.graghql.service.command.ProblemzCommandService;
import com.gql.graghql.service.query.ProblemzQueryService;
import com.gql.graghql.service.query.UserzQueryService;
import com.gql.graghql.util.GraphqlBeanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Controller
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemzQueryService queryService;
    private final ProblemzCommandService commandService;
    private final UserzQueryService userzQueryService;
    private final ProblemzCommandService problemzCommandService;

    @SchemaMapping(
            typeName = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.ProblemLatestList
    )
    public List<Problem> getProblemLatestList(){
        return queryService.problemLatestList();
    }

    @SchemaMapping(
            typeName = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.ProblemDetail
    )
    public Problem getProblemDetail(@Argument(name = "id") String id){
        return queryService.getProblemDetail(id)
                .map(GraphqlBeanMapper::mapToGraphql)
                .orElseThrow(() -> new NotFoundException("problem not found"));
    }

    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.ProblemCreate
    )
    public ProblemResponse createProblem(@Argument(name = "problem") ProblemCreateInput problemCreateInput,
                                         @ContextValue(name = "authToken") String authToken){

        Userz userzByAuthToken = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(() -> new AuthenticationException("Userz does not exist with authToken: " + authToken));

        Problemz problemz = commandService.createProblemz(GraphqlBeanMapper.mapToEntity(problemCreateInput, userzByAuthToken));

        return ProblemResponse.newBuilder().problem(GraphqlBeanMapper.mapToGraphql(problemz)).build();
    }

    @SchemaMapping(
            typeName = DgsConstants.SUBSCRIPTION_TYPE,
            field = DgsConstants.SUBSCRIPTION.ProblemAdded
    )
    public Flux<Problem> subScribeProblemAdded(){
        return problemzCommandService.problemzFlux()
                .map(GraphqlBeanMapper::mapToGraphql);
    }
}
