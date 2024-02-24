package com.iba.signal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.iba.signal.DTO.SignalDTO;
import com.iba.signal.controller.SignalController;
import com.iba.signal.modal.Signals;
import com.iba.signal.service.SignalService;

import jakarta.servlet.http.HttpServletRequest;

class SignalControllerTests {

    @Mock
    private SignalService signalService;

    @InjectMocks
    private SignalController signalController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllSignals_Success() {
        // Mock SignalService
        List<Signals> mockSignals = new ArrayList<>();
        mockSignals.add(new Signals(/* construct signal object */));
        mockSignals.add(new Signals(/* construct signal object */));
        when(signalService.getAllSignals()).thenReturn(mockSignals);

        // Call the method under test
        ResponseEntity<List<Signals>> responseEntity = signalController.getAllSignals();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockSignals, responseEntity.getBody());
    }

    @Test
    void testGetSignalByNodeId_Success() {
        // Mock SignalService
        String nodeId = "exampleNodeId";
        Signals mockSignal = new Signals(/* construct signal object */);
        when(signalService.getSignalByNodeId(nodeId)).thenReturn(mockSignal);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/" + nodeId);

        // Call the method under test
        ResponseEntity<Signals> responseEntity = signalController.getSignalByNodeId(mockRequest);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockSignal, responseEntity.getBody());
    }

    @Test
    void testCreateSignal_Success() {
        // Mock SignalService
        Signals createdSignal = new Signals(/* construct signal object */);
        when(signalService.createSignal(any())).thenReturn(createdSignal);

        // Call the method under test
        ResponseEntity<Signals> responseEntity = signalController.createSignal(new Signals(/* construct signal object */));

        // Verify the response
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdSignal, responseEntity.getBody());
    }

    @Test
    void testUpdateSignal_Success() {
        // Mock SignalService
        String nodeId = "exampleNodeId";
        Signals updatedSignal = new Signals(/* construct signal object */);
        when(signalService.updateSignal(eq(nodeId), any())).thenReturn(updatedSignal);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/" + nodeId);

        // Call the method under test
        ResponseEntity<Signals> responseEntity = signalController.updateSignal(mockRequest, new Signals(/* construct signal object */));

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedSignal, responseEntity.getBody());
    }

    @Test
    void testDeleteSignal_Success() {
        // Mock SignalService
        String nodeId = "exampleNodeId";
        when(signalService.deleteSignal(nodeId)).thenReturn(true);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/" + nodeId);

        // Call the method under test
        ResponseEntity<Void> responseEntity = signalController.deleteSignal(mockRequest);

        // Verify the response
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

        @Test
    void testGetAllSignalsWithKeywords_Success() {
        // Mock SignalService
        List<SignalDTO> mockSignalDTOs = new ArrayList<>();
        mockSignalDTOs.add(new SignalDTO(/* construct SignalDTO object */));
        mockSignalDTOs.add(new SignalDTO(/* construct SignalDTO object */));
        when(signalService.getAllSignalsWithKeywords()).thenReturn(mockSignalDTOs);

        // Call the method under test
        ResponseEntity<List<SignalDTO>> responseEntity = signalController.getAllSignalsWithKeywords();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockSignalDTOs, responseEntity.getBody());
    }

    @Test
    void testGetSignalWithKeywordsByNodeId_Success() {
        // Mock SignalService
        String nodeId = "exampleNodeId";
        SignalDTO mockSignalDTO = new SignalDTO(/* construct SignalDTO object */);
        when(signalService.getSignaWithKeywordlByNodeId(nodeId)).thenReturn(mockSignalDTO);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/withKeywords/" + nodeId);

        // Call the method under test
        ResponseEntity<SignalDTO> responseEntity = signalController.getSignalWithKeywordsByNodeId(mockRequest);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockSignalDTO, responseEntity.getBody());
    }

    @Test
    void testDeleteSignal_NotFound() {
        // Mock SignalService
        String nodeId = "exampleNodeId";
        when(signalService.deleteSignal(nodeId)).thenReturn(false);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/" + nodeId);

        // Call the method under test
        ResponseEntity<Void> responseEntity = signalController.deleteSignal(mockRequest);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetSignalByNodeId_NotFound() {
        // Mock SignalService
        String nodeId = "nonExistentNodeId";
        when(signalService.getSignalByNodeId(nodeId)).thenReturn(null);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/" + nodeId);

        // Call the method under test
        ResponseEntity<Signals> responseEntity = signalController.getSignalByNodeId(mockRequest);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetSignalWithKeywordsByNodeId_NotFound() {
        // Mock SignalService
        String nodeId = "nonExistentNodeId";
        when(signalService.getSignaWithKeywordlByNodeId(nodeId)).thenReturn(null);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/withKeywords/" + nodeId);

        // Call the method under test
        ResponseEntity<SignalDTO> responseEntity = signalController.getSignalWithKeywordsByNodeId(mockRequest);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateSignal_NotFound() {
        // Mock SignalService
        String nodeId = "nonExistentNodeId";
        Signals signalDetails = new Signals(); // Create signal details
        when(signalService.updateSignal(nodeId, signalDetails)).thenReturn(null);

        // Create a mock HttpServletRequest
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/api/signals/" + nodeId);

        // Call the method under test
        ResponseEntity<Signals> responseEntity = signalController.updateSignal(mockRequest, signalDetails);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}