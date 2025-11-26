package com.green.pipeline_dummy.entity.game.mapping;

import com.green.pipeline_dummy.entity.game.Game;
import com.green.pipeline_dummy.entity.game.searchtag.Device;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_device")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GameDevice {

    @EmbeddedId
    private GameDeviceId id;

    @ManyToOne
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("deviceId")
    @JoinColumn(name = "device_id")
    private Device device;
}
