package com.gql.graghql.controller.fake;

import com.gql.graghql.codegen.DgsConstants;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

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
