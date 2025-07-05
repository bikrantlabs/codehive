package com.codehive.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Snippet extends Entity {
    private String title;
    private String content;
    private String language;
    private Integer userId; // Foreign key to User entity
    private boolean isPublic; // Indicates if the snippet is public or private
}
