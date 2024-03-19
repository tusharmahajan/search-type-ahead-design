package com.example.searchtypeaheaddesign.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "query_frequency", indexes = {@Index(name = "query_index", columnList = "query")})
@Data
public class QueryFrequency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "query")
    private String query;

    @Column(name = "frequency")
    private int frequency = 1;
}
