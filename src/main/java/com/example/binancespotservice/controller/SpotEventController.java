package com.example.binancespotservice.controller;

import com.example.binancespotservice.dto.SpotEvent;
import com.example.binancespotservice.service.SpotEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("spot/event")
public class SpotEventController {

    private SpotEventService service;

    @Autowired
    public SpotEventController(SpotEventService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public SpotEvent create(@RequestBody SpotEvent spotEvent) {
        return service.save(spotEvent);
    }

    @PutMapping("{id}")
    @ResponseBody
    public SpotEvent update(@PathVariable Integer id, @RequestBody SpotEvent spotEvent) {
        return service.update(id, spotEvent);
    }

    @GetMapping("{id}")
    @ResponseBody
    public SpotEvent get(@PathVariable Integer id) {
        return service.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return HttpStatus.OK.getReasonPhrase();
    }

    @GetMapping("{symbol}/price/last")
    @ResponseBody
    public Float getLastPriceBySymbol(@PathVariable String symbol) {
        return this.service.getLastPriceBySymbol(symbol);
    }
}
