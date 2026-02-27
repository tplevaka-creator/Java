package com.example.deals.service;

import com.example.deals.model.Deal;
import com.example.deals.repository.DealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealServiceTest {
    private DealService service;
    private DealRepository repo;

    @BeforeEach
    void setUp() {
        repo = new DealRepository();
        service = new DealService(repo);
        repo.clear();
    }

    @Test
    void addAndGetDeal() {
        Deal deal = new Deal(null, "Test", "desc", 5.0, "http://a");
        Deal saved = service.addDeal(deal);
        assertNotNull(saved.getId());
        Deal fetched = service.getDeal(saved.getId());
        assertEquals("Test", fetched.getTitle());
    }

    @Test
    void searchDeals() {
        service.addDeal(new Deal(null, "alpha", "", 1, ""));
        service.addDeal(new Deal(null, "beta", "", 2, ""));
        List<Deal> all = service.searchDeals(null);
        assertEquals(2, all.size());
        List<Deal> filtered = service.searchDeals("bet");
        assertEquals(1, filtered.size());
        assertEquals("beta", filtered.get(0).getTitle());
    }

    @Test
    void updateAndDelete() {
        Deal d = service.addDeal(new Deal(null, "x", "", 1, ""));
        service.updateDeal(d.getId(), new Deal(null, "y", "", 2, ""));
        Deal updated = service.getDeal(d.getId());
        assertEquals("y", updated.getTitle());
        service.deleteDeal(d.getId());
        assertNull(service.getDeal(d.getId()));
    }
}
