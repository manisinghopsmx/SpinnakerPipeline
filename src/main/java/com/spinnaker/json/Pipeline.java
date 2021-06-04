package com.spinnaker.json;

import java.util.List;

public class Pipeline {
    private Boolean keepWaitingPipelines;
    private Boolean limitConcurrent;
    private String application;
    private String spelEvaluator;
    private String name;

    public Boolean getKeepWaitingPipelines() {
        return keepWaitingPipelines;
    }

    public void setKeepWaitingPipelines(Boolean keepWaitingPipelines) {
        this.keepWaitingPipelines = keepWaitingPipelines;
    }

    public Boolean getLimitConcurrent() {
        return limitConcurrent;
    }

    public void setLimitConcurrent(Boolean limitConcurrent) {
        this.limitConcurrent = limitConcurrent;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getSpelEvaluator() {
        return spelEvaluator;
    }

    public void setSpelEvaluator(String spelEvaluator) {
        this.spelEvaluator = spelEvaluator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<String> getStages() {
        return stages;
    }

    public void setStages(List<String> stages) {
        this.stages = stages;
    }

    public List<String> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<String> triggers) {
        this.triggers = triggers;
    }

    private String index;
    private List<String> stages;
    private List<String> triggers;

    @Override
    public String toString() {
        return "Pipeline{" +
                "keepWaitingPipelines=" + keepWaitingPipelines +
                ", limitConcurrent=" + limitConcurrent +
                ", application='" + application + '\'' +
                ", spelEvaluator='" + spelEvaluator + '\'' +
                ", name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", stages=" + stages +
                ", triggers=" + triggers +
                '}';
    }
}
