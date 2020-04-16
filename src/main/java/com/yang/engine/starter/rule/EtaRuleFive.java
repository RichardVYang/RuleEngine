package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EtaRuleFive implements Rule<String, String, String, String> {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Map<String, String> executeRule(Map<String, String> vo) {
        log.info("entering EtaRuleFive::executeRule");
        Map<String, String> result = new HashMap<>(1);
        //TODO implement ETA rule five
      //  result.put(RuleConstants.ERROR_KEY, "Detail error description");
        result.put(RuleConstants.ETA_KEY, "ETA result");

        return result;

    }

}

