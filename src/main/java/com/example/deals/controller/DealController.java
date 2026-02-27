package com.example.deals.controller;

import com.example.deals.model.Deal;
import com.example.deals.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deals")
public class DealController {
    private final DealService service;

    @Autowired
    public DealController(DealService service) {
        this.service = service;
    }

    @GetMapping
    public List<Deal> list(@RequestParam(value = "q", required = false) String q) {
        return service.searchDeals(q);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deal> get(@PathVariable Long id) {
        Deal deal = service.getDeal(id);
        if (deal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deal);
    }

    @PostMapping
    public ResponseEntity<Deal> create(@RequestBody Deal deal) {
        Deal saved = service.addDeal(deal);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deal> update(@PathVariable Long id, @RequestBody Deal deal) {
        if (service.getDeal(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateDeal(id, deal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteDeal(id);
        return ResponseEntity.noContent().build();
    }
}
