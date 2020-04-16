package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EonRuleOne implements Rule<String, String, String, String> {

    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Map<String, String> executeRule(Map<String, String> vo) {
        Map<String, String> result = new HashMap<>(1);
        log.info("entering EnonRuleOne::executeRule");
       //TODO implement EON rule one

      //  result.put(RuleConstants.ERROR_KEY, "Detail error description");
        result.put(RuleConstants.ETD_KEY, "ETD Estimated time goes here");
        return result;

    }
}

