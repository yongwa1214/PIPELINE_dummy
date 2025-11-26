package com.green.pipeline_dummy.entity.game;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class GameStatusConverter implements AttributeConverter<GameStatus, String> {

    @Override
    public String convertToDatabaseColumn(GameStatus status) {
        return status != null ? status.getCode() : null;
    }

    @Override
    public GameStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;

        for (GameStatus st : GameStatus.values()) {
            if (st.getCode().equals(dbData)) {
                return st;
            }
        }
        return null;
    }
}