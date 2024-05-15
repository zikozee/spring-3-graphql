package com.gql.graghql.controller;

import com.gql.graghql.codegen.DgsConstants;
import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 15 May, 2024
 */

@Controller
public class FakeHeaderParamController {

    @SchemaMapping(
            typeName = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.AdditionalDataOnRequest
    )
    public String additionalDataOnRequest(
            @ContextValue(name="optionalHeader", required = false) String optionalHeader,
            @ContextValue(name="mandatoryHeader") String mandatoryHeader
//            @Argument(name="optionalParam") String optionalParam,
//            @Argument(name="mandatoryParam") String mandatoryParam
    ) {


        return "Optional header: " + optionalHeader + ", " +
                "Mandatory header: " + mandatoryHeader + ", " ;
//                "Optional param: " + optionalParam + ", " +
//                "Mandatory param: " + mandatoryParam + ", ";
    }
}
