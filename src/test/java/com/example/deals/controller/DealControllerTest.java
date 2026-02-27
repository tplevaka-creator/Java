package com.example.deals.controller;

import com.example.deals.model.Deal;
import com.example.deals.service.DealService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DealController.class)
class DealControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private DealService service;

    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        Mockito.reset(service);
    }

    @Test
    void listDeals() throws Exception {
        List<Deal> deals = Arrays.asList(new Deal(1L, "a", "", 1, ""));
        Mockito.when(service.searchDeals(null)).thenReturn(deals);

        mvc.perform(get("/api/deals"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(deals)));
    }

    @Test
    void createDeal() throws Exception {
        Deal toCreate = new Deal(null, "test", "", 0, "");
        Deal saved = new Deal(1L, "test", "", 0, "");
        Mockito.when(service.addDeal(any(Deal.class))).thenReturn(saved);

        mvc.perform(post("/api/deals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(toCreate)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(saved)));
    }
}
