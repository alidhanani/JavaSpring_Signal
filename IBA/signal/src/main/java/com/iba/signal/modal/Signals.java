package com.iba.signal.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Signals {
    @Id
    private String nodeId;
    private Integer samplingIntervalMs;
    private Integer deadbandValue;
    private String deadbandType;
    private Integer active;
    private String keywords;

    // Constructor
    public Signals() {
    }

    // Getters and setters
    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getSamplingIntervalMs() {
        return samplingIntervalMs;
    }

    public void setSamplingIntervalMs(Integer samplingIntervalMs) {
        this.samplingIntervalMs = samplingIntervalMs;
    }

    public Integer getDeadbandValue() {
        return deadbandValue;
    }

    public void setDeadbandValue(Integer deadbandValue) {
        this.deadbandValue = deadbandValue;
    }

    public String getDeadbandType() {
        return deadbandType;
    }

    public void setDeadbandType(String deadbandType) {
        this.deadbandType = deadbandType;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}