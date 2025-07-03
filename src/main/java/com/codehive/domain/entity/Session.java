package com.codehive.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Session extends Entity {

    //    This sessionId is different from "id" column
    private String sessionId;

    private Integer userId;

    private LocalDateTime expiresAt;

    private boolean isActive;
}
