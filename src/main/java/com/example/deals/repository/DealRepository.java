package com.example.deals.repository;

import com.example.deals.model.Deal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class DealRepository {
    private final List<Deal> deals = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Deal> findAll() {
        return new ArrayList<>(deals);
    }

    public Optional<Deal> findById(Long id) {
        return deals.stream().filter(d -> d.getId().equals(id)).findFirst();
    }

    public List<Deal> findByTitleContaining(String term) {
        return deals.stream()
                .filter(d -> d.getTitle() != null && d.getTitle().toLowerCase().contains(term.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Deal save(Deal deal) {
        if (deal.getId() == null) {
            deal.setId(counter.getAndIncrement());
        } else {
            deals.removeIf(d -> d.getId().equals(deal.getId()));
        }
        deals.add(deal);
        return deal;
    }

    public void delete(Long id) {
        deals.removeIf(d -> d.getId().equals(id));
    }

    public void clear() {
        deals.clear();
        counter.set(1);
    }
}
