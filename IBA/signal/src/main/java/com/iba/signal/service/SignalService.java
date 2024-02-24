package com.iba.signal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iba.signal.modal.Signals;
import com.iba.signal.repository.SignalRepository;

import java.util.List;

@Service
public class SignalService {
    @Autowired
    private SignalRepository signalRepository;
    
    public List<Signals> getAllSignals() {
        return signalRepository.findAll();
    }
    
    public Signals getSignalByNodeId(String nodeId) {
        return signalRepository.findById(nodeId).orElse(null);
    }
    
    public Signals createSignal(Signals signal) {
        return signalRepository.save(signal);
    }
    
    public Signals updateSignal(String nodeId, Signals signalDetails) {
        Signals signal = signalRepository.findById(nodeId).orElse(null);
        if (signal == null) {
            return null;
        }
        signal.setSamplingIntervalMs(signalDetails.getSamplingIntervalMs());
        signal.setDeadbandValue(signalDetails.getDeadbandValue());
        signal.setDeadbandType(signalDetails.getDeadbandType());
        signal.setActive(signalDetails.getActive());
        signal.setKeywords(signalDetails.getKeywords());
        return signalRepository.save(signal);
    }
    
    public boolean deleteSignal(String nodeId) {
        Signals signal = signalRepository.findById(nodeId).orElse(null);
        if (signal == null) {
            return false;
        }
        signalRepository.delete(signal);
        return true;
    }
}

