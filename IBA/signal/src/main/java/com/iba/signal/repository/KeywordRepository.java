package com.iba.signal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iba.signal.modal.Keywords;

@Repository
public interface KeywordRepository extends JpaRepository<Keywords, Long> {
    Keywords findByName(String name);
}

