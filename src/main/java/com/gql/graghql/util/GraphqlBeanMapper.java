package com.gql.graghql.util;

import com.gql.graghql.codegen.types.*;
import com.gql.graghql.entity.Problemz;
import com.gql.graghql.entity.Solutionz;
import com.gql.graghql.entity.Userz;
import com.gql.graghql.entity.UserzToken;
import com.gql.graghql.exception.ProcessException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@UtilityClass
public class GraphqlBeanMapper {

    private final PrettyTime PRETTY_TIME = new PrettyTime();
    private final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(8);


    public User mapToGraphql(Userz entity){
        User user = new User();
        LocalDateTime createdDateTime = entity.getCreationTimestamp().atOffset(ZONE_OFFSET).toLocalDateTime();

        user.setAvatar(entity.getAvatar());
        user.setCreatedDateTime(createdDateTime);
        user.setDisplayName(entity.getDisplayName());
        user.setEmail(entity.getEmail());
        user.setId(entity.getId().toString());
        user.setUsername(entity.getUsername());

        return user;
    }

    public Problem mapToGraphql(Problemz entity){
        Problem problem = new Problem();

        LocalDateTime createdDateTime = entity.getCreationTimestamp();
        User author = mapToGraphql(entity.getCreatedBy());
        List<Solution> convertedSolutions = entity.getSolutions()
                .stream()
//                .sorted(Comparator.comparing(Solutionz::getCreationTimestamp).reversed())
                        .map(GraphqlBeanMapper::mapToGraphql)
                                .toList();

        List<String> tagList = List.of(entity.getTags().split(","));

        problem.setAuthor(author);
        problem.setContent(entity.getContent());
        problem.setCreatedDateTime(createdDateTime);
        problem.setId(entity.getId().toString());
        problem.setSolutions(convertedSolutions);
        problem.setTags(tagList);
        problem.setTitle(entity.getTitle());
        problem.setSolutionCount(convertedSolutions.size());
        problem.setPrettyCreatedDateTime(PRETTY_TIME.format(createdDateTime));

        return problem;
    }

    public Solution mapToGraphql(Solutionz entity){
        Solution solution = new Solution();
        LocalDateTime createdDateTime = entity.getCreationTimestamp().atOffset(ZONE_OFFSET).toLocalDateTime();
        User author = mapToGraphql(entity.getCreatedBy());
        SolutionCategory solutionCategory = StringUtils.equalsIgnoreCase(entity.getCategory(), SolutionCategory.EXPLANATION.name())
                ? SolutionCategory.EXPLANATION : SolutionCategory.REFERENCE;

        solution.setAuthor(author);
        solution.setCategory(solutionCategory);
        solution.setContent(entity.getContent());
        solution.setCreatedDateTime(createdDateTime);
        solution.setId(entity.getId().toString());
        solution.setVoteAsBadCount(entity.getVoteBadCount());
        solution.setVoteAsGoodCount(entity.getVoteGoodCount());
        solution.setPrettyCreatedDateTime(PRETTY_TIME.format(createdDateTime));

        return solution;
    }

    public UserAuthToken mapToGraphql(UserzToken entity){
        UserAuthToken authToken = new UserAuthToken();
        LocalDateTime expiryDateTime = entity.getExpiryTimestamp().atOffset(ZONE_OFFSET).toLocalDateTime();

        authToken.setAuthToken(entity.getAuthToken());
        authToken.setExpiryTime(expiryDateTime);
        return authToken;
    }

    public Problemz mapToEntity(ProblemCreateInput original, Userz author){
        Problemz problemz = new Problemz();

        problemz.setId(UUID.randomUUID());
        problemz.setContent(original.getContent());
        problemz.setCreatedBy(author);
        problemz.setSolutions(Collections.emptyList());
        problemz.setTags(String.join(",", original.getTags()));
        problemz.setTitle(original.getTitle());

        return problemz;
    }

    public Solutionz mapToEntity(SolutionCreateInput original, Userz author, Problemz problemz){
        Solutionz solutionz = new Solutionz();

        solutionz.setId(UUID.randomUUID());
        solutionz.setCategory(original.getCategory().name());
        solutionz.setContent(original.getContent());
        solutionz.setCreatedBy(author);
        solutionz.setProblemz(problemz);

        return solutionz;
    }


    public UUID uuidFromString(String uuidString){

        try {
            return UUID.fromString(uuidString);
        }catch (Exception e){
            throw new ProcessException("invalid UUID from string: '" + uuidString + "'");
        }
    }

    public Userz mapToEntity(UserCreateInput original){
        Userz userz = new Userz();

        userz.setId(UUID.randomUUID());
        userz.setHashedPassword(HashUtil.hashBcrypt(original.getPassword()));
        userz.setUsername(original.getUsername());
        userz.setEmail(original.getEmail());
        userz.setDisplayName(original.getDisplayName());
        userz.setAvatar(original.getAvatar());
        userz.setActive(true);

        return userz;
    }

}
