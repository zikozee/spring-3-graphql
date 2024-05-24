package com.gql.graghql.controller.problemz;

import com.gql.graghql.codegen.DgsConstants;
import com.gql.graghql.codegen.types.Solution;
import com.gql.graghql.codegen.types.SolutionCreateInput;
import com.gql.graghql.codegen.types.SolutionResponse;
import com.gql.graghql.codegen.types.SolutionVoteInput;
import com.gql.graghql.entity.Problemz;
import com.gql.graghql.entity.Solutionz;
import com.gql.graghql.entity.Userz;
import com.gql.graghql.exception.AuthenticationException;
import com.gql.graghql.exception.NotFoundException;
import com.gql.graghql.service.command.SolutionzCommandService;
import com.gql.graghql.service.query.ProblemzQueryService;
import com.gql.graghql.service.query.SolutionzQueryService;
import com.gql.graghql.service.query.UserzQueryService;
import com.gql.graghql.util.GraphqlBeanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Controller
@RequiredArgsConstructor
public class SolutionController {

    private final UserzQueryService userzQueryService;
    private final ProblemzQueryService problemzQueryService;
    private final SolutionzCommandService solutionzCommandService;

    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.SolutionCreate
    )
    public SolutionResponse createSolution(@ContextValue(name = "authToken") String authToken,
                                           @Argument(name = "solution") SolutionCreateInput solutionCreateInput){

        Userz userzByAuthToken = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(() -> new AuthenticationException("Auth not valid"));

        Problemz problemz = problemzQueryService.getProblemDetail(solutionCreateInput.getProblem())
                .orElseThrow(() -> new NotFoundException("Problem not found"));

        Solutionz solutionz = GraphqlBeanMapper.mapToEntity(solutionCreateInput, userzByAuthToken, problemz);

        Solutionz createdSolutionz = solutionzCommandService.createSolutionz(solutionz);
        return SolutionResponse.newBuilder()
                .solution(GraphqlBeanMapper.mapToGraphql(createdSolutionz)).build();
    }

    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.SolutionVote
    )
    public SolutionResponse createSolutionVote(@ContextValue(name = "authToken") String authToken,
                                               @Argument(name = "vote")SolutionVoteInput solutionVoteInput){

        Optional<Solutionz> updated;
        Userz userzToken = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(() -> new AuthenticationException("Auth not valid"));
        if(solutionVoteInput.getVoteAsGood()){
            updated =solutionzCommandService.voteGood(
                    UUID.fromString(solutionVoteInput.getSolutionId())
            );
        }else {
            updated =solutionzCommandService.voteBad(
                    UUID.fromString(solutionVoteInput.getSolutionId())
            );
        }
        if(updated.isEmpty()) throw new NotFoundException("Solution not found");
        return SolutionResponse
                .newBuilder()
                .solution(GraphqlBeanMapper.mapToGraphql(updated.get()))
                .build();
    }

    @SchemaMapping(
            typeName = DgsConstants.SUBSCRIPTION_TYPE,
            field = DgsConstants.SUBSCRIPTION.SolutionVoteChanged
    )
    public Flux<Solution> subscribeSolution(@Argument(name = "solutionId") String solutionId){
        return solutionzCommandService.solutionzFlux()
                .map(GraphqlBeanMapper::mapToGraphql);
    }
}
