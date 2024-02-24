package com.iba.signal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iba.signal.modal.Signals;
import com.iba.signal.service.SignalService;

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
    public ResponseEntity<Signals> getSignalByNodeId(@PathVariable("nodeId") String nodeId) {
        Signals signal = signalService.getSignalByNodeId(nodeId);
        if (signal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(signal, HttpStatus.OK);
    }
}
