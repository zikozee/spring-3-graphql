package com.gql.graghql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "userz")
@Getter @Setter
public class Userz {

    @Id
    private UUID id;
    private String username;
    private String email;
    private String hashedPassword;
    private String avatar;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    private String displayName;
    private boolean active;
}
