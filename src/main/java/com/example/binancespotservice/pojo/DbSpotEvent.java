package com.example.binancespotservice.pojo;

import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "spot_event")
public class DbSpotEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "price")
    private Float price;

    @Column(name = "agg_id")
    private Long aggId;

    @Column(name = "event_time")
    private Timestamp eventTime;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "first_id")
    private Long firstId;

    @Column(name = "is_buyer_maker")
    private Boolean isBuyerMaker;

    @Column(name = "last_id")
    private Long lastId;

    @Column(name = "trade_time")
    private Timestamp tradeTime;

    @Column(name = "is_ignore")
    private Boolean isIgnore;

    @Column(name = "quantity")
    private Float quantity;

    public Timestamp getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Timestamp tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public Long getFirstId() {
        return firstId;
    }

    public void setFirstId(Long firstId) {
        this.firstId = firstId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {this.eventTime = eventTime;}

    public Long getAggId() {
        return aggId;
    }

    public void setAggId(Long aggId) {
        this.aggId = aggId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getBuyerMaker() {
        return isBuyerMaker;
    }

    public void setBuyerMaker(Boolean buyerMaker) {
        isBuyerMaker = buyerMaker;
    }

    public Boolean getIgnore() {
        return isIgnore;
    }

    public void setIgnore(Boolean ignore) {
        this.isIgnore = ignore;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }
}

