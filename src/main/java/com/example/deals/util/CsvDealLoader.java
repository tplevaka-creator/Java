package com.example.deals.util;

import com.example.deals.model.Deal;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvDealLoader {
    /**
     * Parse deals from a CSV reader. Expects header: title,description,price,url
     */
    public List<Deal> load(Reader reader) throws IOException, CsvException {
        List<Deal> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> rows = csvReader.readAll();
            boolean first = true;
            for (String[] row : rows) {
                if (first) { // skip header
                    first = false;
                    continue;
                }
                if (row.length < 4) continue;
                Deal deal = new Deal();
                deal.setTitle(row[0]);
                deal.setDescription(row[1]);
                deal.setPrice(Double.parseDouble(row[2]));
                deal.setUrl(row[3]);
                list.add(deal);
            }
        }
        return list;
    }
}
