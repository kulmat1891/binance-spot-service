package com.example.binancespotservice.service;

import com.example.binancespotservice.converter.SpotEventConverter;
import com.example.binancespotservice.dto.SpotEvent;
import com.example.binancespotservice.exception.EntityNotFoundException;
import com.example.binancespotservice.pojo.DbSpotEvent;
import com.example.binancespotservice.repository.SpotEventRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotEventService {

    private SpotEventRepository repository;

    private SpotEventConverter converter;

    @Autowired
    public SpotEventService(SpotEventRepository repository,
                            SpotEventConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public SpotEvent save(SpotEvent dtoEvent) {
        DbSpotEvent dbEvent = this.converter.toDb(dtoEvent);
        DbSpotEvent dbSavedEvent = this.repository.save(dbEvent);
        return this.converter.toDto(dbSavedEvent);
    }

    public void saveAll(List<SpotEvent> events) {
        List<DbSpotEvent> dbEvents = events.stream().map(converter::toDb).collect(Collectors.toList());
        StreamSupport.stream(this.repository.saveAll(dbEvents).spliterator(), false);
    }

    public SpotEvent update(Integer id, SpotEvent spotEvent) {
        DbSpotEvent currentDbEvent = this.repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Entity not found id = " + id)
        );
        DbSpotEvent newDbEvent = this.converter.toDb(spotEvent);
        newDbEvent.setId(currentDbEvent.getId());

        DbSpotEvent dbSavedEvent = this.repository.save(newDbEvent);
        return this.converter.toDto(dbSavedEvent);
    }

    public SpotEvent get(Integer id) {
        DbSpotEvent dbEvent = this.repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Entity not found id = " + id)
        );
        return this.converter.toDto(dbEvent);
    }

    public void delete(Integer id) {
        this.repository.deleteById(id);
    }

    public Float getLastPriceBySymbol(String symbol) {
        return this.repository.getLastPriceBySymbol(symbol);
    }
}