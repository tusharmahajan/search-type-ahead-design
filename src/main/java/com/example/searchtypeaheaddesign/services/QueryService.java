package com.example.searchtypeaheaddesign.services;

import com.example.searchtypeaheaddesign.models.QueryFrequency;
import com.example.searchtypeaheaddesign.repository.QueryRepository;
import com.example.searchtypeaheaddesign.services.asyncProcessor.SuggestionAggregatorThread;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class QueryService {

    @Autowired
    private QueryRepository queryRepository;

    @Autowired
    private SuggestionAggregatorThread suggestionAggregatorThread;

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public String storeOrUpdateQuery(String query) {
//        System.out.println(executorService.hashCode());

        int rowsAffected = queryRepository.incrementFrequency(query);
        if(rowsAffected == 0){
            QueryFrequency queryFrequency = new QueryFrequency();
            queryFrequency.setQuery(query);
            queryRepository.save(queryFrequency);
            executorService.submit(suggestionAggregatorThread);
            return "Query saved successfully";
        }
        executorService.submit(suggestionAggregatorThread);

        return "Frequency of query incremented";
    }

}
