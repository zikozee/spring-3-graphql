package com.gql.graghql.controller.problemz;

import com.gql.graghql.codegen.DgsConstants;
import com.gql.graghql.codegen.types.*;
import com.gql.graghql.entity.Userz;
import com.gql.graghql.entity.UserzToken;
import com.gql.graghql.exception.AuthenticationException;
import com.gql.graghql.exception.NotFoundException;
import com.gql.graghql.service.command.UserzCommandService;
import com.gql.graghql.service.query.UserzQueryService;
import com.gql.graghql.util.GraphqlBeanMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.ContextValue;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserzQueryService userzQueryService;
    private final UserzCommandService userzCommandService;

    @SchemaMapping(
            typeName = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.Me
    )
    public User accountInfo(@ContextValue(name="authToken") String token){
        Userz userz = userzQueryService.findUserzByAuthToken(token).get();
        return GraphqlBeanMapper.mapToGraphql(userz);
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
////    @Secured("ROLE_ADMIN")
    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.UserCreate
    )
    public UserResponse createUser(@Argument(name = "user")UserCreateInput userCreateInput
            , @ContextValue(name="authToken") String authToken){
        Userz userAuth = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(AuthenticationException::new);

        if(!StringUtils.equals(userAuth.getUserRole(), "ROLE_ADMIN"))
            throw new AuthenticationException();

        Userz userz = GraphqlBeanMapper.mapToEntity(userCreateInput);
        Userz savedUserz = userzCommandService.createUser(userz);

        return UserResponse.newBuilder().user(GraphqlBeanMapper.mapToGraphql(savedUserz)).build();
    }

    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.UserLogin
    )
    public UserResponse userLogin(@Argument(name = "user") UserLoginInput userLoginInput){
        UserzToken login = userzCommandService.login(userLoginInput.getUsername(), userLoginInput.getPassword());

        UserAuthToken userAuthToken = GraphqlBeanMapper.mapToGraphql(login);
        User userInfo = accountInfo(userAuthToken.getAuthToken());
        return UserResponse.newBuilder()
                .user(userInfo)
                .authToken(userAuthToken)
                .build();
    }

//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
    @SchemaMapping(
            typeName = DgsConstants.MUTATION.TYPE_NAME,
            field = DgsConstants.MUTATION.UserActivation
    )
    public UserActivationResponse userActivation(@Argument(name = "user") UserActivationInput userActivationInput
            , @ContextValue(name="authToken") String authToken ){

        Userz userAuth = userzQueryService.findUserzByAuthToken(authToken)
                .orElseThrow(AuthenticationException::new);

        if(!StringUtils.equals(userAuth.getUserRole(), "ROLE_ADMIN"))
            throw new AuthenticationException();

        Userz userz = userzCommandService.activeUser(userActivationInput.getUsername(), userActivationInput.getActive())
                .orElseThrow(NotFoundException::new);
        return UserActivationResponse.newBuilder().isActive(userz.isActive()).build();
    }
}
