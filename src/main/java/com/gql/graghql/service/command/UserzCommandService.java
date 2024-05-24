package com.gql.graghql.service.command;

import com.gql.graghql.entity.Userz;
import com.gql.graghql.entity.UserzToken;
import com.gql.graghql.repository.UserzRepository;
import com.gql.graghql.repository.UserzTokenRepository;
import com.gql.graghql.exception.AuthenticationException;
import com.gql.graghql.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Service
@RequiredArgsConstructor
public class UserzCommandService {
    private final UserzRepository userzRepository;
    private final UserzTokenRepository userzTokenRepository;

    public UserzToken login(String username, String password) {
        Optional<Userz> optionalUserz = userzRepository.findByUsernameIgnoreCase(username);

        if(optionalUserz.isEmpty() || !HashUtil.isBcryptMatch(password, optionalUserz.get().getHashedPassword())) {
            throw new AuthenticationException("Invalid Credentials");
        }

        String randomAuthToken = RandomStringUtils.randomAlphanumeric(40);

        return refreshToken(optionalUserz.get().getId(), randomAuthToken);
    }

    private UserzToken refreshToken(UUID userId, String authToken) {
        UserzToken userzToken = new UserzToken();

        userzToken.setUserId(userId);
        userzToken.setAuthToken(authToken);

        LocalDateTime now = LocalDateTime.now();
        userzToken.setCreationTimestamp(now);
        userzToken.setExpiryTimestamp(now.plusHours(2));

        return userzTokenRepository.save(userzToken);
    }
}
