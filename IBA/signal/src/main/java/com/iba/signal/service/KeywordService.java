package com.iba.signal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iba.signal.modal.Keywords;
import com.iba.signal.repository.KeywordRepository;

import java.util.List;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;
    
    public List<Keywords> getAllKeywords() {
        return keywordRepository.findAll();
    }
    
    public Keywords getKeywordById(Long id) {
        return keywordRepository.findById(id).orElse(null);
    }
    
    public Keywords createKeyword(Keywords keyword) {
        return keywordRepository.save(keyword);
    }
    
    public Keywords updateKeyword(Long id, Keywords keywordDetails) {
        Keywords keyword = keywordRepository.findById(id).orElse(null);
        if (keyword == null) {
            return null;
        }
        keyword.setName(keywordDetails.getName());
        keyword.setDescription(keywordDetails.getDescription());
        return keywordRepository.save(keyword);
    }
    
    public boolean deleteKeyword(Long id) {
        Keywords keyword = keywordRepository.findById(id).orElse(null);
        if (keyword == null) {
            return false;
        }
        keywordRepository.delete(keyword);
        return true;
    }
}

