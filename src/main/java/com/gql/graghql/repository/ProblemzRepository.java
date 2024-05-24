package com.gql.graghql.repository;

import com.gql.graghql.entity.Problemz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Repository
public interface ProblemzRepository extends CrudRepository<Problemz, UUID> {

    List<Problemz> findAllByOrderByCreationTimestamp();
    @Query(nativeQuery = true,
            value = "select * from problemz p " +
            "where upper(p.content) like upper(?1) " +
            "or upper(p.title) like upper(?1) " +
            "or upper(p.tags) like upper(?1)")
    List<Problemz> findByKeyword(String keyword);
}
