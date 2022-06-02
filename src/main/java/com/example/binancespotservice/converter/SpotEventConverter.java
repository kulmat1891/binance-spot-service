package com.example.binancespotservice.converter;

import com.example.binancespotservice.dto.SpotEvent;
import com.example.binancespotservice.pojo.DbSpotEvent;
import java.util.Optional;
import org.springframework.stereotype.Component;


@Component
public class SpotEventConverter {

    public DbSpotEvent toDb(SpotEvent dto) {
        DbSpotEvent db = new DbSpotEvent();
        Optional.ofNullable(dto.getSymbol()).ifPresent(db::setSymbol);
        Optional.ofNullable(dto.getPrice()).ifPresent(db::setPrice);
        Optional.ofNullable(dto.getEventType()).ifPresent(db::setEventType);
        Optional.ofNullable(dto.getEventTime()).ifPresent(db::setEventTime);
        Optional.ofNullable(dto.getAggId()).ifPresent(db::setAggId);
        Optional.ofNullable(dto.getQuantity()).ifPresent(db::setQuantity);
        Optional.ofNullable(dto.getFirstId()).ifPresent(db::setFirstId);
        Optional.ofNullable(dto.getLastId()).ifPresent(db::setLastId);
        Optional.ofNullable(dto.getTradeTime()).ifPresent(db::setTradeTime);
        Optional.ofNullable(dto.getBuyerMaker()).ifPresent(db::setBuyerMaker);
        Optional.ofNullable(dto.getIgnore()).ifPresent(db::setIgnore);
        return db;
    }

    public SpotEvent toDto(DbSpotEvent db) {
        SpotEvent dto = new SpotEvent();
        Optional.ofNullable(db.getSymbol()).ifPresent(dto::setSymbol);
        Optional.ofNullable(db.getPrice()).ifPresent(dto::setPrice);
        Optional.ofNullable(db.getEventType()).ifPresent(dto::setEventType);
        Optional.ofNullable(db.getEventTime()).ifPresent(dto::setEventTime);
        Optional.ofNullable(db.getAggId()).ifPresent(dto::setAggId);
        Optional.ofNullable(db.getQuantity()).ifPresent(dto::setQuantity);
        Optional.ofNullable(db.getFirstId()).ifPresent(dto::setFirstId);
        Optional.ofNullable(db.getLastId()).ifPresent(dto::setLastId);
        Optional.ofNullable(db.getTradeTime()).ifPresent(dto::setTradeTime);
        Optional.ofNullable(db.getBuyerMaker()).ifPresent(dto::setBuyerMaker);
        Optional.ofNullable(db.getIgnore()).ifPresent(dto::setIgnore);
        return dto;
    }
}

