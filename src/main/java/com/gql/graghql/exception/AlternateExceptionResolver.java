//package com.gql.graghql.exception;
//
//import graphql.GraphQLError;
//import graphql.execution.DataFetcherExceptionHandler;
//import graphql.execution.DataFetcherExceptionHandlerParameters;
//import graphql.execution.DataFetcherExceptionHandlerResult;
//import graphql.schema.DataFetchingEnvironment;
//import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
//import org.springframework.graphql.execution.ErrorType;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
///**
// * @author : Ezekiel Eromosei
// * @code @created : 24 May, 2024
// */
////alternative to Global Exception NOT SURE ===IT WORKS
//@Component
//public class AlternateExceptionResolver implements DataFetcherExceptionHandler {
//
//
//    @Override
//    public CompletableFuture<DataFetcherExceptionHandlerResult> handleException(DataFetcherExceptionHandlerParameters handlerParameters) {
//        Throwable ex = handlerParameters.getException();
//        DataFetchingEnvironment env = handlerParameters.getDataFetchingEnvironment();
//
//
//        if (ex instanceof ProcessException) {
//            return CompletableFuture.completedFuture(
//                    new DataFetcherExceptionHandlerResult.Builder().error(
//                            getBuild(ex, ErrorType.BAD_REQUEST, env)
//                    ).build()
//            );
//        }
//        if (ex instanceof NotFoundException) {
//            System.out.println(">>>>>>>>>>>>>>>>>>>>>IN HERE BABY");
//            return CompletableFuture.completedFuture(
//                    new DataFetcherExceptionHandlerResult.Builder().error(
//                            getBuild(ex, ErrorType.BAD_REQUEST, env)
//                    ).build()
//            );
//        }
//        else {
//            return CompletableFuture.completedFuture(
//                    new DataFetcherExceptionHandlerResult.Builder().error(
//                            getBuild(ex, ErrorType.INTERNAL_ERROR, env)
//                    ).build()
//            );
//        }
//    }
//
//    private static GraphQLError getBuild(Throwable ex, ErrorType errorType, DataFetchingEnvironment env) {
//        return GraphQLError.newError()
//                .message(ex.getLocalizedMessage())
//                .errorType(errorType)
//                .path(env.getExecutionStepInfo().getPath())
//                .location(env.getField().getSourceLocation())
//                .build();
//    }
//}
