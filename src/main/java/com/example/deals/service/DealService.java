package com.example.deals.service;

import com.example.deals.model.Deal;
import com.example.deals.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealService {
    private final DealRepository repository;

    @Autowired
    public DealService(DealRepository repository) {
        this.repository = repository;
    }

    public List<Deal> getAllDeals() {
        return repository.findAll();
    }

    public Deal getDeal(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Deal> searchDeals(String term) {
        if (term == null || term.isBlank()) {
            return getAllDeals();
        }
        return repository.findByTitleContaining(term);
    }

    public Deal addDeal(Deal deal) {
        return repository.save(deal);
    }

    public Deal updateDeal(Long id, Deal deal) {
        deal.setId(id);
        return repository.save(deal);
    }

    public void deleteDeal(Long id) {
        repository.delete(id);
    }

    public void clearDeals() {
        repository.clear();
    }
}
