package com.iba.signal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iba.signal.DTO.SignalDTO;
import com.iba.signal.modal.Keywords;
import com.iba.signal.modal.Signals;
import com.iba.signal.repository.KeywordRepository;
import com.iba.signal.repository.SignalRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SignalService {

    @Autowired
    private KeywordRepository keywordRepository;
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

    public List<SignalDTO> getAllSignalsWithKeywords() {
        List<Signals> signals = signalRepository.findAll();
        List<SignalDTO> signalDTOs = new ArrayList<>();
        for (Signals signal : signals) {
            SignalDTO signalDTO = new SignalDTO();
            signalDTO.setNodeId(signal.getNodeId());
            signalDTO.setSamplingIntervalMs(signal.getSamplingIntervalMs());
            signalDTO.setDeadbandValue(signal.getDeadbandValue());
            signalDTO.setDeadbandType(signal.getDeadbandType());
            signalDTO.setActive(signal.getActive());
    
            String keywordIds = signal.getKeywords();
            List<String> keywordNames = new ArrayList<>();
            
            if (keywordIds != null && !keywordIds.isEmpty()) {
                // Removing leading and trailing brackets and then splitting
                String[] keywordIdsArray = keywordIds.substring(1, keywordIds.length() - 1).split(";");
    
                for (String keywordId : keywordIdsArray) {
                    // Trim to remove any leading or trailing whitespace
                    keywordId = keywordId.trim();
                    if (!keywordId.isEmpty()) { // Add this check
                        Long id = Long.parseLong(keywordId);
                        Keywords keyword = keywordRepository.findById(id).orElse(null);
                        if (keyword != null) {
                            keywordNames.add(keyword.getName());
                        }
                    }
                }
            }
            signalDTO.setKeywords(keywordNames);
    
            signalDTOs.add(signalDTO);
        }
        return signalDTOs;
    }
    
    public SignalDTO getSignaWithKeywordlByNodeId(String nodeId) {
        Signals signal = signalRepository.findById(nodeId).orElse(null);
        if (signal == null) {
            // Signal not found for the given nodeId
            return null;
        }
        
        SignalDTO signalDTO = new SignalDTO();
        signalDTO.setNodeId(signal.getNodeId());
        signalDTO.setSamplingIntervalMs(signal.getSamplingIntervalMs());
        signalDTO.setDeadbandValue(signal.getDeadbandValue());
        signalDTO.setDeadbandType(signal.getDeadbandType());
        signalDTO.setActive(signal.getActive());
    
        String keywordIds = signal.getKeywords();
        List<String> keywordNames = new ArrayList<>();
        if (keywordIds != null && !keywordIds.isEmpty()) {
            // Removing leading and trailing brackets and then splitting
            String[] keywordIdsArray = keywordIds.substring(1, keywordIds.length() - 1).split(";");
    
            for (String keywordId : keywordIdsArray) {
                // Trim to remove any leading or trailing whitespace
                keywordId = keywordId.trim();
                if (!keywordId.isEmpty()) { // Add this check
                    Long id = Long.parseLong(keywordId);
                    Keywords keyword = keywordRepository.findById(id).orElse(null);
                    if (keyword != null) {
                        keywordNames.add(keyword.getName());
                    }
                }
            }
        }
        signalDTO.setKeywords(keywordNames);
    
        return signalDTO;
    }
    
    public Signals createSignalsWithKeywords(SignalDTO signals) {
        Signals signal = new Signals();
        signal.setNodeId(signals.getNodeId());
        signal.setSamplingIntervalMs(signals.getSamplingIntervalMs());
        signal.setDeadbandValue(signals.getDeadbandValue());
        signal.setDeadbandType(signals.getDeadbandType());
        signal.setActive(signals.getActive());
        
        // Convert keyword names to IDs
        String keywordIds = convertKeywordNamesToIds(signals.getKeywords());
        signal.setKeywords(keywordIds);

        return signalRepository.save(signal);
    }

    public Signals updateSignalsWithKeywords(String nodeId, SignalDTO signals) {
        Signals signalToUpdate = signalRepository.findById(nodeId).orElse(null);
        if (signalToUpdate == null) {
            return null; // Signal not found
        }
        // Update signal properties
        signalToUpdate.setNodeId(signals.getNodeId());
        signalToUpdate.setSamplingIntervalMs(signals.getSamplingIntervalMs());
        signalToUpdate.setDeadbandValue(signals.getDeadbandValue());
        signalToUpdate.setDeadbandType(signals.getDeadbandType());
        signalToUpdate.setActive(signals.getActive());
        // Convert keyword names to IDs
        String keywordIds = convertKeywordNamesToIds(signals.getKeywords());
        signalToUpdate.setKeywords(keywordIds);
        return signalRepository.save(signalToUpdate);
    }
    
    public boolean deleteSignalsWithKeywordByNodeId(String nodeId) {
        Signals signalToDelete = signalRepository.findById(nodeId).orElse(null);
        if (signalToDelete == null) {
            return false; // Signal not found
        }
        signalRepository.delete(signalToDelete);
        return true;
    }

    private String convertKeywordNamesToIds(List<String> keywordNames) {
        if (keywordNames == null || keywordNames.isEmpty()) {
            return "";
        }
        System.out.println(keywordNames);
        List<String> keywordIds = new ArrayList<>();
        for (String name : keywordNames) {
            Keywords keyword = keywordRepository.findByName(name);
            System.out.println(keyword);
            if (keyword != null) {
                keywordIds.add(keyword.getId().toString());
            }
        }
        return "{" + String.join(";", keywordIds) + "}";
    }
    
}

