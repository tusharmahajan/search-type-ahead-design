package com.example.searchtypeaheaddesign.models;

import java.util.List;

public interface SuggestionDataStructure {

    void init();
    List<Suggestion> getTopSuggestions(String query);
}
