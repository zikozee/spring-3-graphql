package com.gql.graghql.service.query;

import com.gql.graghql.entity.Userz;
import com.gql.graghql.repository.UserzRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Service
@RequiredArgsConstructor
public class UserzQueryService {
    private final UserzRepository userzRepository;

    public Optional<Userz> findUserzByAuthToken(String authToken) {
        return userzRepository.findUserByToken(authToken);
    }
}
