package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EstimatedETA implements Rule<String, String, String, String> {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Map<String, String> executeRule(Map<String, String> vo) {
        log.info("entering EstimatedETA::executeRule");
        Map<String, String> result = new HashMap<>(1);
        //TODO implement ETA rule
        result.put(RuleConstants.ETA_KEY, "ETA Estimated time goes here");

        return result;
    }
}
