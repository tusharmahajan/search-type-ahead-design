package com.example.searchtypeaheaddesign.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Suggestion {

    private final String word;
    private final int frequency;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suggestion that = (Suggestion) o;
        return frequency == that.frequency && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, frequency);
    }
}
