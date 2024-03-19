package com.example.searchtypeaheaddesign.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TopSuggestionsCache implements SuggestionCache{

    private final SuggestionDataStructure suggestionDataStructure;

    @Autowired
    public TopSuggestionsCache(SuggestionDataStructure suggestionDataStructure){
        this.suggestionDataStructure = suggestionDataStructure;
    }

    @Override
    public List<Suggestion> getTopSuggestions(String query) {
        return suggestionDataStructure.getTopSuggestions(query);
    }
}
