package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EstimatedEON implements Rule<String, String, String, String> {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Map<String, String> executeRule(Map<String, String> vo) {
        log.info("entering EstimatedEON::executeRule");
        //TODO implement EON rule
        Map<String, String> result = new HashMap<>(1);
        result.put(RuleConstants.EON_KEY, "EON Estimated time goes here");

        return result;
    }
}
