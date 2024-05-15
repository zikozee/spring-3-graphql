package com.gql.graghql.config.interceptor;

import org.aopalliance.intercept.Interceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


import java.util.Map;
import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 15 May, 2024
 */

@Component
public class RequestHeaderInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        String optionalHeader = request.getHeaders().getFirst("optionalHeader");
        String mandatoryHeader = request.getHeaders().getFirst("mandatoryHeader");

        request.configureExecutionInput((executionInput, builder) ->
                builder.graphQLContext(Map.of("optionalHeader", StringUtils.defaultIfBlank(optionalHeader, StringUtils.EMPTY), "mandatoryHeader", StringUtils.defaultIfBlank(mandatoryHeader, StringUtils.EMPTY))).build());
        return chain.next(request);
    }
}
