package com.example.searchtypeaheaddesign.controllers;

import com.example.searchtypeaheaddesign.models.Suggestion;
import com.example.searchtypeaheaddesign.services.QueryService;
import com.example.searchtypeaheaddesign.services.SuggestionAggregatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search-portal")
public class SearchTypeAheadController {

    @Autowired
    private QueryService queryService;

    @Autowired
    private SuggestionAggregatorService suggestionAggregatorService;

    @PostMapping("/query")
    public String createQuery(String query){
        return queryService.storeOrUpdateQuery(query);
    }

    @GetMapping("/suggestions")
    public List<Suggestion> getTopSuggestions(@RequestParam String prefix){
        return suggestionAggregatorService.getTopSuggestions(prefix);
    }
}
