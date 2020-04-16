package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RuleManager {
  private Logger log = LoggerFactory.getLogger(this.getClass().getName());

  private Map<String, Map<String, Rule>> eventRule;
  private Map<String, Rule> estimatedTime;

  private ContextManager contextManager;


  private void addResult(Map<String, String> tvo, Map<String, String> result) {
    if (null != result) {
      result.entrySet().forEach(entry -> tvo.put(entry.getKey(), entry.getValue())); // over-write existing value if keys are same.
    }
  }

  /**
   * @param event is the event type such as ETA, ETD, ETO, EON and etc
   * @return a map containing keys and their respective rules for a given event name
   */
  public Map<String, Rule> getEventRule(String event) {
    log.info("eventName: " + event);
    Map<String, Rule> rules = eventRule.get(event);

    return rules;
  }

  /**
   * @param event is the context of the event where it is originated. It could be an external
   *     system or a particular user. A context specifies which rule, how many rule, and the order
   *     of selected rules to be executed.
   * @return an orderly list of rule names matching this event context
   */
  public List<String> getRuleForContext(String event) {
    log.info("eventContext: " + event);
    List<String> ruleContexts = contextManager.getContext(event);

    return ruleContexts;
  }

  /**
   * @param ruleName is the rule to be executed
   * @param event is the event type such as ETA, ETD, ETO, EON and etc
   * @param vo contains any value the rule need to use for processing
   * @return One or multiple key-value or one key-value error with completed error code with detail
   *     description to the original caller
   * @description this method enables caller the flexibility to execute one rule at a time allowing
   *     the caller to do some other processing between rule execution.
   */
  public Map<String, String> executeRuleForEvent(
      String ruleName, String event, Map<String, String> vo) {
    log.info("ruleName: " + ruleName + ". eventName: " + event);
    Map<String, Rule> rule = getEventRule(event);
    Map<String, String> result = rule.get(ruleName).executeRule(vo);

    return result;
  }

  /**
   * @param eventContext is the context of the event where it is originated. It could be an external
   *     system or a particular user. A context specifies which rule, how many rule, and the order
   *     of selected rules to be executed.
   * @param eventName is the rule category such as ETA, ETD, ETO, EON, and etc.
   * @param vo contains any value the rule needs to use for processing
   * @return One or multiple key-value or one key-value error with completed error code with detail
   *     description to the original caller
   */
  public Map<String, String> executeRuleForContextAndEvent(
      String eventContext, String eventName, Map<String, String> vo) {
    log.info("eventContext: " + eventContext + ". eventName: " + eventName);

    Map<String, String> tvo = new HashMap<>();

    List<String> contextRuleNames = getRuleForContext(eventContext);
    Map<String, Rule> rules = getEventRule(eventName);

    for (String rule : contextRuleNames) {
      Map<String, String> result = rules.get(rule).executeRule(vo);
      addResult(tvo, result);
      if ((null != result) && ((null != result.get(RuleConstants.ERROR_KEY)))) {
        log.info("**************** Error has occurred: " + result.get(RuleConstants.ERROR_KEY));
        break; // stop if error has occurred.
      }
    }

    return tvo;
  }

  /**
   * This method will return an order list of estimated time names that need to calculate and
   * cascade according to the eventName. Eventname of ETD will return ETO, EON and ETA. ETO will
   * return EON and ETA. And EON will return ETA.
   *
   * @param event is the event name such as ETD,ETO, EON, and ETA.
   * @return a Hashmap containing the cascading key-value(s).
   */
  public List<String> getCascadeEstimatedRuleName(String event) {
    List<String> ruleNames = contextManager.getEstimatedTimeRuleName(event);

    return ruleNames;
  }


  /**
   * This method will calculate the estimated times and cascading according to the eventName. An ETD
   * will update an ETO, EON and ETA.  An ETO will update an EON and ETA and an EON will update an
   * ETA
   *
   * @param event is the event name such as ETD,ETO, EON, and ETA.
   * @param vo contains the input data
   * @return a Hashmap containing the cascading key-value(s).
   */
  public Map<String, String> executeCascadeEstimatedTime(String event, Map<String, String>vo) {
    Map<String, String> tvo = new HashMap<>();


    List<String> cascadeRules = getCascadeEstimatedRuleName(event);
    cascadeRules.forEach(cascadeRule-> {
      Map<String, String> result = estimatedTime.get(cascadeRule).executeRule(vo);
      addResult(tvo, result);
    });

    return tvo;
  }

  /**
   *
   * @param rules is an orderly list containing all user defined rule names
   * @param vo is the user defined data to be used for each rule
   * @return a map collection containing all values for all rules.  Matching key will contain the last value.
   */
  public Map<String, String> executeEstimatedRule(List<String>rules, Map<String, String>vo) {
    Map<String, String> tvo = new HashMap<>();

    rules.forEach(rule -> {
      Map<String, String> result = executeEstimatedRule(rule, vo);
      addResult(tvo, result);
    });

    return tvo;
  }


  /**
   *
   * @param ruleName is a user defined rule name to be executed
   * @param vo is the user defined data to be used for this rule execution
   * @return a map containing key-value pair of the result in executing the rule
   */
  public Map<String, String> executeEstimatedRule(String ruleName, Map<String, String>vo) {
    Map<String, String> result = new HashMap<>();

      Rule rule = estimatedTime.get(ruleName);
      if (null != rule) {
        result = rule.executeRule(vo);
      }

    return result;
  }

  // getters and setters
  public Map<String, Map<String, Rule>> getEventRule() {
    return eventRule;
  }

  public void setEventRule(Map<String, Map<String, Rule>> eventRule) {
    this.eventRule = eventRule;
  }


  public ContextManager getContextManager() {
    return contextManager;
  }

  public void setContextManager(ContextManager contextManager) {
    this.contextManager = contextManager;
  }


  public Map<String, Rule> getEstimatedTime() {
    return estimatedTime;
  }

  public void setEstimatedTime(Map<String, Rule> estimatedTime) {
    this.estimatedTime = estimatedTime;
  }


}
