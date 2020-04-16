package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * There may not be a business rule to process this.  The ETD value is provided to do other cascading and time estimating.
 * Therefore, this rule can be deleted after confirmation.
 */
public class EstimatedETD implements Rule<String, String, String, String> {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Map<String, String> executeRule(Map<String, String> vo) {
        log.info("entering EstimatedETD::executeRule");
        Map<String, String> result = new HashMap<>(1);
        result.put(RuleConstants.ETD_KEY, "ETD Estimated times goes here");

        return result;
    }
}
