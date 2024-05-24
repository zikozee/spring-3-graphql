package com.gql.graghql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Entity
@Table(name = "solutionz")
@Getter @Setter
public class Solutionz {

    @Id
    private UUID id;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    private String content;
    private String category;
    private int voteGoodCount;
    private int voteBadCount;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Userz createdBy;

    @ManyToOne
    @JoinColumn(name = "problemz_id", nullable = false)
    private Problemz problemz;
}
