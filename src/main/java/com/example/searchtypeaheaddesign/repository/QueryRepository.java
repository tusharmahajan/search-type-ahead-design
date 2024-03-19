package com.example.searchtypeaheaddesign.repository;

import com.example.searchtypeaheaddesign.models.QueryFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QueryRepository extends JpaRepository<QueryFrequency, Integer> {

    @Transactional
    @Modifying
    @Query("update QueryFrequency q set q.frequency = q.frequency + 1 where q.query = :query")
    int incrementFrequency(String query);
}
