package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EtoRuleOne implements Rule<String, String, String, String> {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public Map<String, String> executeRule(Map<String, String> vo) {
        log.info("entering EtoRuleOne::executeRule");
        //TODO implement ETO rule one

        Map<String, String> result = new HashMap<>(1);
        result.put(RuleConstants.ETO_KEY, "ETO result");
     //   result.put(RuleConstants.ERROR_KEY, "Detail error description");
        return result;
    }
}
