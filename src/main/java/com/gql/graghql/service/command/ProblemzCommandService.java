package com.gql.graghql.service.command;

import com.gql.graghql.entity.Problemz;
import com.gql.graghql.repository.ProblemzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 24 May, 2024
 */

@Service
@RequiredArgsConstructor
public class ProblemzCommandService {
    private final Sinks.Many<Problemz> problemzSink = Sinks.many().multicast().onBackpressureBuffer();

    private final ProblemzRepository repository;

    public Problemz createProblemz(Problemz problemz) {
        Problemz save = repository.save(problemz);

        problemzSink.tryEmitNext(save);

        return save;
    }

    public Flux<Problemz> problemzFlux() {
        return problemzSink.asFlux();
    }
}
