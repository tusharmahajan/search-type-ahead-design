package com.example.searchtypeaheaddesign.models;

import com.example.searchtypeaheaddesign.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SuggestionTrie implements SuggestionDataStructure{

    private QueryRepository queryRepository;

    private volatile TrieNode root;
    private final int k = Constants.MAX_SUGGESTION_SIZE;

    @Autowired
    public SuggestionTrie(QueryRepository queryRepository){
        this.queryRepository = queryRepository;
        init();
    }

    @Override
    public void init() {
        this.root = new TrieNode();
        List<QueryFrequency> queryFrequencyList = queryRepository.findAll();
        for(QueryFrequency queryFrequency: queryFrequencyList){
            insert(queryFrequency.getQuery(), 0, queryFrequency.getFrequency(), root);
        }
    }

    public void insert(String query, int index, int frequency, TrieNode current) {

        if(index == query.length()){
            Set<Suggestion> suggestions = new HashSet<>(current.getTopSuggestions());
            suggestions.add(new Suggestion(query, frequency));
            updateTopSuggestions(suggestions, current);
            return;
        }
        int charIndex = query.charAt(index)-'a';

        TrieNode next = current.getChildren()[charIndex];
        if(next == null){
            current.getChildren()[charIndex] = new TrieNode();
        }
        insert(query, index+1, frequency, current.getChildren()[charIndex]);
        if(index == query.length()-1){
            current.setEndOfWord(true);
        }

        Set<Suggestion> suggestions = new HashSet<>(current.getTopSuggestions());
        for(int i = 0;i<26;i++){
            if(current.getChildren()[i] != null){
                suggestions.addAll(current.getChildren()[i].getTopSuggestions());
            }
        }
        updateTopSuggestions(suggestions, current);
    }

    private void updateTopSuggestions(Set<Suggestion> suggestions, TrieNode current) {
        List<Suggestion> suggestionList = new ArrayList<>(suggestions);

        Collections.sort(suggestionList, new Comparator<>() {
            @Override
            public int compare(Suggestion o1, Suggestion o2) {
                return o2.getFrequency()-o1.getFrequency();
            }
        });

        List<Suggestion> finalSuggestionList = new ArrayList<>();
        for(int i = 0;i < Math.min(k, suggestionList.size());i++){
            finalSuggestionList.add(suggestionList.get(i));
        }
        current.setTopSuggestions(finalSuggestionList);
    }

    @Override
    public List<Suggestion> getTopSuggestions(String query) {
        TrieNode current = root;
        for(int i = 0;i<query.length();i++){
            int currIndex = query.charAt(i)-'a';
            if(current.getChildren()[currIndex] == null) return Collections.emptyList();
            current = current.getChildren()[currIndex];
        }
        return current.getTopSuggestions();
    }
}
