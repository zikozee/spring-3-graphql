package com.gql.graghql.security;


import com.gql.graghql.entity.Userz;
import com.gql.graghql.repository.UserzRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProblemzAuthenticationProvider implements AuthenticationProvider {

    private final UserzRepository userzRepository;

    public ProblemzAuthenticationProvider(UserzRepository userzRepository) {
        this.userzRepository = userzRepository;
    }

    @Override
    public Authentication authenticate(Authentication auth) {
         Userz user = userzRepository.findUserByToken(auth.getCredentials().toString())
                .orElse(new Userz());

        return new UsernamePasswordAuthenticationToken(user, auth.getCredentials().toString(),
                getAuthorities(user.getUserRole()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String userRole) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (StringUtils.isNotBlank(userRole)) {
            authorities.add(new SimpleGrantedAuthority(userRole));
        }

        return authorities;
    }

}
