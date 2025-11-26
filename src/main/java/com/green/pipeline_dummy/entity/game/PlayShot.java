package com.green.pipeline_dummy.entity.game;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "play_shot")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class PlayShot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screenshot_id")
    private Long screenshotId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    // 이미지 or 영상 URL
    @Column(length = 255)
    private String media;

    @Enumerated(EnumType.STRING)
    @Column(name = "media_type")
    private MediaType mediaType;

    public enum MediaType {
        image, video
    }
}
