package com.example.deals.util;

import com.example.deals.model.Deal;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvDealLoaderTest {
    @Test
    void loadValidCsv() throws Exception {
        String csv = "title,description,price,url\n" +
                "A,Desc,10,https://x\n" +
                "B,Desc2,20,https://y";
        CsvDealLoader loader = new CsvDealLoader();
        List<Deal> deals = loader.load(new StringReader(csv));
        assertEquals(2, deals.size());
        assertEquals("A", deals.get(0).getTitle());
    }

    @Test
    void loadEmpty() throws Exception {
        CsvDealLoader loader = new CsvDealLoader();
        List<Deal> deals = loader.load(new StringReader("title,description,price,url\n"));
        assertTrue(deals.isEmpty());
    }
}
