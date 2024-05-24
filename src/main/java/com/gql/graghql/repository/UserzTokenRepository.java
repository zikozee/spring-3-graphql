package com.gql.graghql.repository;

import com.gql.graghql.entity.UserzToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Repository
public interface UserzTokenRepository extends CrudRepository<UserzToken, UUID> {
}
