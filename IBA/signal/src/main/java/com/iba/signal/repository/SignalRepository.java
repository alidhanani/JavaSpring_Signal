package com.iba.signal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iba.signal.modal.Signals;

@Repository
public interface SignalRepository extends JpaRepository<Signals, String> {
}
