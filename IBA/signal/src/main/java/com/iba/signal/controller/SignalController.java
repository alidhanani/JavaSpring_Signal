package com.iba.signal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iba.signal.DTO.SignalDTO;
import com.iba.signal.modal.Signals;
import com.iba.signal.service.SignalService;

import jakarta.servlet.http.HttpServletRequest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/api/signals")
public class SignalController {

    @Autowired
    private SignalService signalService;

    @GetMapping
    public ResponseEntity<List<Signals>> getAllSignals() {
        List<Signals> signals = signalService.getAllSignals();
        return new ResponseEntity<>(signals, HttpStatus.OK);
    }

    @GetMapping("/{nodeId}")
    public ResponseEntity<Signals> getSignalByNodeId(HttpServletRequest request) {
        // Extracting the full path from the request URL
        String fullPath = request.getRequestURI();

        // Extracting nodeId from the full path
        String[] pathSegments = fullPath.split("/");
        String nodeId = pathSegments[pathSegments.length - 1]; // Last segment
        
        // URL decode the nodeId string
        String decodedNodeId = URLDecoder.decode(nodeId, StandardCharsets.UTF_8);
        Signals signal = signalService.getSignalByNodeId(decodedNodeId);
        if (signal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(signal, HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Signals> createSignal(@RequestBody Signals signal) {
        Signals createdSignal = signalService.createSignal(signal);
        return new ResponseEntity<>(createdSignal, HttpStatus.CREATED);
    }

    @PutMapping("/{nodeId}")
    public ResponseEntity<Signals> updateSignal(HttpServletRequest request, @RequestBody Signals signalDetails) {
        // Extracting the full path from the request URL
        String fullPath = request.getRequestURI();

        // Extracting nodeId from the full path
        String[] pathSegments = fullPath.split("/");
        String nodeId = pathSegments[pathSegments.length - 1]; // Last segment
        
        // URL decode the nodeId string
        String decodedNodeId = URLDecoder.decode(nodeId, StandardCharsets.UTF_8);
        Signals updatedSignal = signalService.updateSignal(decodedNodeId, signalDetails);
        if (updatedSignal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedSignal, HttpStatus.OK);
    }

    @DeleteMapping("/{nodeId}")
    public ResponseEntity<Void> deleteSignal(HttpServletRequest request) {
        // Extracting the full path from the request URL
        String fullPath = request.getRequestURI();

        // Extracting nodeId from the full path
        String[] pathSegments = fullPath.split("/");
        String nodeId = pathSegments[pathSegments.length - 1]; // Last segment
        
        // URL decode the nodeId string
        String decodedNodeId = URLDecoder.decode(nodeId, StandardCharsets.UTF_8);
        boolean deleted = signalService.deleteSignal(decodedNodeId);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/withKeywords")
    public ResponseEntity<List<SignalDTO>> getAllSignalsWithKeywords() {
        List<SignalDTO> signalsWithKeywords = signalService.getAllSignalsWithKeywords();
        return new ResponseEntity<>(signalsWithKeywords, HttpStatus.OK);
    }

    @GetMapping("/withKeywords/{nodeId}")
    public ResponseEntity<SignalDTO> getSignalWithKeywordsByNodeId(HttpServletRequest request) {
        // Extracting the full path from the request URL
        String fullPath = request.getRequestURI();
        
        // Extracting nodeId from the full path
        String[] pathSegments = fullPath.split("/");
        String nodeId = pathSegments[pathSegments.length - 1]; // Last segment
        
        // URL decode the nodeId string
        String decodedNodeId = URLDecoder.decode(nodeId, StandardCharsets.UTF_8);
        SignalDTO signal = signalService.getSignaWithKeywordlByNodeId(decodedNodeId);
        if (signal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(signal, HttpStatus.OK);
    }

}
