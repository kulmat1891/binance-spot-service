package com.example.binancespotservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SpotEvent {

    @JsonProperty("s")
    private String symbol;

    @JsonProperty("p")
    private Float price;

    @JsonProperty("e")
    private String eventType;

    @JsonProperty("E")
    private Timestamp eventTime;

    @JsonProperty("a")
    private Long aggId;

    @JsonProperty("q")
    private Float quantity;

    @JsonProperty("f")
    private Long firstId;

    @JsonProperty("l")
    private Long lastId;

    @JsonProperty("T")
    private Timestamp tradeTime;

    @JsonProperty("m")
    private Boolean isBuyerMaker;

    @JsonProperty("M")
    private Boolean isIgnore;

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

    @Override
    public String toString() {
        return "SpotEvent{" +
                "symbol='" + symbol + '\'' +
                ", price='" + price + '\'' +
                ", eventType='" + eventType + '\'' +
                ", eventTime=" + eventTime +
                ", aggId=" + aggId +
                ", quantity=" + quantity +
                ", firstId=" + firstId +
                ", lastId=" + lastId +
                ", tradeTime=" + tradeTime +
                ", isBuyerMaker=" + isBuyerMaker +
                ", isIgnore=" + isIgnore +
                '}';
    }

}