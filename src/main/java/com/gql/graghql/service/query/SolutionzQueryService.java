package com.gql.graghql.service.query;

import com.gql.graghql.entity.Solutionz;
import com.gql.graghql.repository.SolutionzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Service
@RequiredArgsConstructor
public class SolutionzQueryService {
    private final SolutionzRepository solutionzRepository;

    public List<Solutionz> problemByKeyword(String keyword) {
        return solutionzRepository.findByKeyword("%" + keyword + "%");
    }
}
