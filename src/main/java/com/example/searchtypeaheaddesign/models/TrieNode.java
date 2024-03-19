package com.example.searchtypeaheaddesign.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TrieNode {

    private final TrieNode[] children = new TrieNode[26];
    private boolean isEndOfWord = false;
    private List<Suggestion> topSuggestions = new ArrayList<>();
}
