package com.iba.signal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iba.signal.modal.Keywords;
import com.iba.signal.service.KeywordService;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
public class KeywordController {

    @Autowired
    private KeywordService keywordService;

    @GetMapping
    public ResponseEntity<List<Keywords>> getAllKeywords() {
        List<Keywords> keywords = keywordService.getAllKeywords();
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Keywords> getKeywordById(@PathVariable("id") Long id) {
        Keywords keyword = keywordService.getKeywordById(id);
        if (keyword == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(keyword, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Keywords> createKeyword(@RequestBody Keywords keyword) {
        Keywords createdKeyword = keywordService.createKeyword(keyword);
        return new ResponseEntity<>(createdKeyword, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Keywords> updateKeyword(@PathVariable("id") Long id, @RequestBody Keywords keywordDetails) {
        Keywords updatedKeyword = keywordService.updateKeyword(id, keywordDetails);
        if (updatedKeyword == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedKeyword, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKeyword(@PathVariable("id") Long id) {
        boolean deleted = keywordService.deleteKeyword(id);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

