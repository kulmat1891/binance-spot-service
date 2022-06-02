package com.example.binancespotservice.repository;

import com.example.binancespotservice.pojo.DbSpotEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SpotEventRepository extends CrudRepository<DbSpotEvent, Integer> {

    @Query(value = "SELECT price FROM spot_event WHERE symbol = :symbol ORDER BY event_time DESC LIMIT 1", nativeQuery = true)
    Float getLastPriceBySymbol(@Param("symbol") String symbol);
}