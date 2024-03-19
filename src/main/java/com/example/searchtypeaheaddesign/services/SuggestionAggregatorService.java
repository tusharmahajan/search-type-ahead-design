package com.example.searchtypeaheaddesign.services;

import com.example.searchtypeaheaddesign.models.SuggestionCache;
import com.example.searchtypeaheaddesign.models.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionAggregatorService {

    @Autowired
    private SuggestionCache suggestionCache;

    public List<Suggestion> getTopSuggestions(String prefix) {
        return suggestionCache.getTopSuggestions(prefix);
    }
}
