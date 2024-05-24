package com.gql.graghql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 May, 2024
 */

@Entity
@Table(name = "userz_token")
@Getter @Setter @ToString
public class UserzToken {

    @Id
    private UUID userId;
    private String authToken;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    private LocalDateTime expiryTimestamp;
}
