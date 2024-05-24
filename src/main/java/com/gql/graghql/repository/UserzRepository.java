package com.gql.graghql.repository;

import com.gql.graghql.entity.Userz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Repository
public interface UserzRepository extends CrudRepository<Userz, UUID> {

    Optional<Userz> findByUsernameIgnoreCase(String username);

    @Query(nativeQuery = true, value = "select u.* "
            + "from userz u inner join userz_token ut "
            + "on u.id = ut.user_id "
            + " where ut.auth_token = ?1 "
            + "and ut.expiry_timestamp > current_timestamp"
    )
    Optional<Userz> findUserByToken(String authToken);
}
