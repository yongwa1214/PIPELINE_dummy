package com.green.pipeline_dummy.entity.game;

import com.green.pipeline_dummy.entity.user.DeveloperProfile;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long gameId;

    // ===========================
    // 1) base_game_id (Self FK)
    // ===========================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_game_id")
    private Game baseGame;     // DLC, 확장팩, 시리즈 등을 위한 부모 게임 FK

    // ===========================
    // 2) dp_profile_id (FK)
    // ===========================
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dp_profile_id")
    private DeveloperProfile developerProfile;

    // ===========================
    // 3) 일반 필드
    // ===========================
    @Column(name = "game_name", length = 100, nullable = false)
    private String gameName;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "publisher", length = 50)
    private String publisher;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 255)
    private String thumbnail;

    @Convert(converter = GameStatusConverter.class)
    @Column(name = "status", nullable = false)
    private GameStatus status;

    @Column(name = "total_download", nullable = false)
    private Integer totalDownload;
}
