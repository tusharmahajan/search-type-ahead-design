package com.example.searchtypeaheaddesign.services.asyncProcessor;

import com.example.searchtypeaheaddesign.models.SuggestionDataStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SuggestionAggregatorThread implements Runnable {

    private final SuggestionDataStructure suggestionDataStructure;

    @Autowired
    public SuggestionAggregatorThread(SuggestionDataStructure suggestionDataStructure){
        this.suggestionDataStructure = suggestionDataStructure;
    }

    @Override
    public void run() {
        suggestionDataStructure.init();
    }

}
