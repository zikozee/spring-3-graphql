package com.gql.graghql.service.command;

import com.gql.graghql.entity.Solutionz;
import com.gql.graghql.entity.Userz;
import com.gql.graghql.entity.UserzToken;
import com.gql.graghql.exception.AuthenticationException;
import com.gql.graghql.repository.SolutionzRepository;
import com.gql.graghql.repository.UserzRepository;
import com.gql.graghql.repository.UserzTokenRepository;
import com.gql.graghql.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Service
@RequiredArgsConstructor
public class SolutionzCommandService {


    private final SolutionzRepository solutionzRepository;

    private final Sinks.Many<Solutionz> solutionzSink = Sinks.many().multicast().onBackpressureBuffer();

    public Solutionz createSolutionz(Solutionz solutionz) {
        return solutionzRepository.save(solutionz);
    }

    public Optional<Solutionz> voteBad(UUID solutionId) {
        solutionzRepository.addVoteBadCount(solutionId);
        Optional<Solutionz> updated = solutionzRepository.findById(solutionId);

        if (updated.isPresent()) {
            solutionzSink.tryEmitNext(updated.get());
        }

        return updated;
    }

    public Optional<Solutionz> voteGood(UUID solutionId) {
        solutionzRepository.addVoteGoodCount(solutionId);
        Optional<Solutionz> updated = solutionzRepository.findById(solutionId);

        if (updated.isPresent()) {
            solutionzSink.tryEmitNext(updated.get());
        }

        return updated;
    }

    public Flux<Solutionz> solutionzFlux() {
        return solutionzSink.asFlux();
    }
}
