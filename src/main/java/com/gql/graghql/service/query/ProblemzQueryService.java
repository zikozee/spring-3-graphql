package com.gql.graghql.service.query;

import com.gql.graghql.codegen.types.Problem;
import com.gql.graghql.entity.Problemz;
import com.gql.graghql.repository.ProblemzRepository;
import com.gql.graghql.exception.NotFoundException;
import com.gql.graghql.util.GraphqlBeanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Service
@RequiredArgsConstructor
public class ProblemzQueryService {
    private final ProblemzRepository problemzRepository;

    public List<Problem> problemLatestList() {
        return problemzRepository.findAllByOrderByCreationTimestamp()
                .stream()
                .map(GraphqlBeanMapper::mapToGraphql)
                .toList();
    }

    public Optional<Problemz> getProblemDetail(String id) {
        return problemzRepository.findById(GraphqlBeanMapper.uuidFromString(id));
    }

    public List<Problemz> problemByKeyword(String keyword) {
        return problemzRepository.findByKeyword("%" + keyword + "%");
    }
}
