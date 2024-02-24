package com.iba.signal.DTO;

import java.util.List;

public class SignalDTO {
    private String nodeId;
    private Integer samplingIntervalMs;
    private Integer deadbandValue;
    private String deadbandType;
    private Integer active; // Change to Integer
    private List<String> keywords;

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

    public void setActive(Integer integer) {
        this.active = integer;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
