package com.green.pipeline_dummy.application.library.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LibraryDto {
    Long ownerId;
    Long itemId;
    String libStatus;
    int playtime; //초단위
    LocalDateTime lastPlay;
    int playNow;
    LocalDateTime gotAt;
}
