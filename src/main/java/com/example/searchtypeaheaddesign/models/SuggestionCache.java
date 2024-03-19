package com.example.searchtypeaheaddesign.models;

import java.util.List;

public interface SuggestionCache {
    List<Suggestion> getTopSuggestions(String query);
}
