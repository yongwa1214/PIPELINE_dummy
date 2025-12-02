package com.green.pipeline_dummy.entity.game;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_challenge")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameChallenge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Long challengeId;

    @Column(name = "game_id", nullable = false)
    private Long gameId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "clear_user_count")
    private Integer clearUserCount;

    @Column(name = "hidden")
    private Integer hidden;

    @Column(name = "challenge_pic", length = 255)
    private String challengePic;
}
