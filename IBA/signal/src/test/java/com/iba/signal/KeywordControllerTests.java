package com.iba.signal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.iba.signal.controller.KeywordController;
import com.iba.signal.modal.Keywords;
import com.iba.signal.service.KeywordService;

class KeywordControllerTests {

 @Mock
    private KeywordService keywordService;

    @InjectMocks
    private KeywordController keywordController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllKeywords_Success() {
        // Mock KeywordService
        List<Keywords> mockKeywords = new ArrayList<>();
        mockKeywords.add(new Keywords(1L, "Test Keyword 1", "Description 1"));
        mockKeywords.add(new Keywords(2L, "Test Keyword 2", "Description 2"));
        when(keywordService.getAllKeywords()).thenReturn(mockKeywords);

        // Call the method under test
        ResponseEntity<List<Keywords>> responseEntity = keywordController.getAllKeywords();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockKeywords, responseEntity.getBody());
    }

    @Test
    void testGetKeywordById_Success() {
        // Mock KeywordService
        Keywords mockKeyword = new Keywords(1L, "Test Keyword", "Description");
        when(keywordService.getKeywordById(1L)).thenReturn(mockKeyword);

        // Call the method under test
        ResponseEntity<Keywords> responseEntity = keywordController.getKeywordById(1L);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockKeyword, responseEntity.getBody());
    }

    @Test
    void testGetKeywordById_NotFound() {
        // Mock KeywordService
        when(keywordService.getKeywordById(1L)).thenReturn(null);

        // Call the method under test
        ResponseEntity<Keywords> responseEntity = keywordController.getKeywordById(1L);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

       @Test
    void testUpdateKeyword_Success() {
        // Mock KeywordService
        Keywords mockKeyword = new Keywords(1L, "Test Keyword", "Description");
        when(keywordService.updateKeyword(eq(1L), any(Keywords.class))).thenReturn(mockKeyword);

        // Call the method under test
        Keywords keywordToUpdate = new Keywords(1L, "Updated Keyword", "Updated Description");
        ResponseEntity<Keywords> responseEntity = keywordController.updateKeyword(1L, keywordToUpdate);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockKeyword, responseEntity.getBody());
    }

    @Test
    void testCreateKeyword_Success() {
        // Mock KeywordService
        Keywords mockKeyword = new Keywords(1L, "Test Keyword", "Description");
        when(keywordService.createKeyword(any(Keywords.class))).thenReturn(mockKeyword);

        // Call the method under test
        Keywords keywordToCreate = new Keywords(1L, "Test Keyword", "Description");
        ResponseEntity<Keywords> responseEntity = keywordController.createKeyword(keywordToCreate);

        // Verify the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(mockKeyword, responseEntity.getBody());
    }

    @Test
    void testDeleteKeyword_Success() {
        // Mock KeywordService
        when(keywordService.deleteKeyword(1L)).thenReturn(true);

        // Call the method under test
        ResponseEntity<Void> responseEntity = keywordController.deleteKeyword(1L);

        // Verify the response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testUpdateKeyword_NotFound() {
        // Mock KeywordService to simulate keyword not found
        when(keywordService.updateKeyword(eq(1L), any(Keywords.class))).thenReturn(null);

        // Call the method under test
        Keywords keywordToUpdate = new Keywords(1L, "Updated Keyword", "Updated Description");
        ResponseEntity<Keywords> responseEntity = keywordController.updateKeyword(1L, keywordToUpdate);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testDeleteKeyword_NotFound() {
        // Mock KeywordService to simulate keyword not found
        when(keywordService.deleteKeyword(1L)).thenReturn(false);

        // Call the method under test
        ResponseEntity<Void> responseEntity = keywordController.deleteKeyword(1L);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

}