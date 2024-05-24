package com.gql.graghql.repository;

import com.gql.graghql.entity.Solutionz;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Repository
public interface SolutionzRepository extends CrudRepository<Solutionz, UUID> {

    @Query(nativeQuery = true,
            value = "select * from solutionz s " +
                    "where upper(s.content) like upper(?1)")
    List<Solutionz> findByKeyword(String keyword);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update solutionz set vote_bad_count= vote_bad_count + 1 where id=?1")
    void addVoteBadCount(UUID id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update solutionz set vote_good_count= vote_good_count + 1 where id=?1")
    void addVoteGoodCount(UUID id);
}
