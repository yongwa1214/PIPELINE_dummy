package com.green.pipeline_dummy.entity.game.mapping;

import lombok.*;
import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GameDeviceId implements Serializable {
    private Long gameId;
    private Long deviceId;
}
