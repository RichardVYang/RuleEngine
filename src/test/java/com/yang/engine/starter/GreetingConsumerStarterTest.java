package com.yang.engine.starter;

import static org.junit.Assert.assertTrue;

import com.yang.engine.starter.rule.ContextManager;
import com.yang.engine.starter.rule.Rule;
import com.yang.engine.starter.rule.RuleManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Start this as a pure Spring Integration application
 */
public class GreetingConsumerStarterTest {
  private Logger log = LoggerFactory.getLogger(this.getClass().getName());


  /**
   * eventContext identifies the key to the contextRule in the ContextManager.
   * eventName identifies the key to the eventRule in the RuleManager.
   */

  @Test
  public void etaRuleOneTest() {
    log.info("========> Running etaRuleOneTest");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");
    log.info("Testing ETA context one with event ETA...");
    Map<String, String>vo = new HashMap<>();  // contains any value for the rule to use for processing
    vo = ruleManager.executeRuleForContextAndEvent("etaContextOne", "eta", vo);
    assertTrue(null != vo);

  }

  /**
   * Pass in: eventContext and eventName
   */

  @Test
  public void etaRuleTwoTest() {
    log.info("========> Running etaRuleTwoTest");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");
    log.info("Testing ETA context two with event ETA...");
    Map<String, String>vo = new HashMap<>();  // contains any value for the rule to use for processing
    vo = ruleManager.executeRuleForContextAndEvent("etaContextTwo", "eta", vo);
    assertTrue(null != vo);

  }

  /**
   * Pass in: eventContext and eventName.
   * Return list of rule name.  Execute one rule at a time.
   */
  @Test
  public void etaOneRuleAtATimeTest() {
    log.info("========> Running etaOneRuleAtATimeTest with etaContextOne and event ETA...");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(
                    "/" + GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager) context.getBean("ruleManager");

    List<String> contextRuleNames = ruleManager.getRuleForContext("etaContextOne");
    Map<String, String>vo = new HashMap<>();  // contains any value for the rule to use for processing
    for (String ruleName : contextRuleNames) {
      log.info("***** executing rule: " + ruleName);
      Map<String, String> result = ruleManager.executeRuleForEvent(ruleName, "eta", vo);
      assertTrue(null != result);
      log.info("Do some other processing....");
    }

  }

  /**
   * Pass in: eventName.  Return a list of rule.
   */
  @Test
  public void etaEventRuleAtCallerTest() {
    log.info("========> Running etaEventRuleAtCallerTest with event ETA");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext(
                    "/" + GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager) context.getBean("ruleManager");

    Map<String, Rule> ruleMap = ruleManager.getEventRule("eta");
    Map<String, String>vo = new HashMap<>();  // contains any value for the rule to use for processing
    ruleMap.forEach((ruleName, rule) -> rule.executeRule(vo)); // do need return value


  }

  @Test
  public void allRulesTest() {
    log.info("========> Running allRulesTest for all event context and all event names...");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");
    Map<String, String>vo = new HashMap<>();  // contains any value for the rule to use for processing
    log.info("Testing ETA context one with event ETA...");
    Map<String, String>result = ruleManager.executeRuleForContextAndEvent("etaContextOne", "eta", vo);
    assertTrue(null != result);
    log.info("Testing ETA context two with event EAT...");
    result = ruleManager.executeRuleForContextAndEvent("etaContextTwo", "eta", vo);
    assertTrue(null != result);
    log.info("Testing ETD context one with etd...");
    result = ruleManager.executeRuleForContextAndEvent("etdContextOne", "etd", vo);
    assertTrue(null != result);
    log.info("Testing ETO context one with event ETO...");
    ruleManager.executeRuleForContextAndEvent("etoContextOne", "eto", vo);
    assertTrue(null != result);
    log.info("Testing EON context one with event eon...");
    result = ruleManager.executeRuleForContextAndEvent("eonContextOne", "eon", vo);
    assertTrue(null != result);

  }

@Test
  public void edtToetaTest() {
  log.info("========> Running edtToetaTest with event ETD");
  ClassPathXmlApplicationContext context =
          new ClassPathXmlApplicationContext("/"+
                  GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                  GreetingConsumerStarterTest.class);

  RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");
  Map<String, String> vo = new HashMap<String, String>(1);
  Map<String, String>result = ruleManager.executeCascadeEstimatedTime("ETD", vo);
  assertTrue(!result.isEmpty());
}

  @Test
  public void etoToetaTest() {
    log.info("========> Running etoToetaTest with event ETO");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");
    Map<String, String> vo = new HashMap<String, String>(1);

    Map<String, String>result = ruleManager.executeCascadeEstimatedTime("ETO", vo);
    assertTrue(!result.isEmpty());
  }

  @Test
  public void eonToetaTest() {
    log.info("========> Running eonToetaTest with event EON");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");
    Map<String, String> vo = new HashMap<String, String>(1);

    Map<String, String>result = ruleManager.executeCascadeEstimatedTime("EON", vo);
    assertTrue(!result.isEmpty());

  }

  @Test
  public void etaToeonTest() {
    log.info("========> Running etaToeonTest with event ETA");
    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");
    Map<String, String> vo = new HashMap<String, String>(1);

    Map<String, String> result = ruleManager.executeCascadeEstimatedTime("ETA", vo);

    assertTrue(!result.isEmpty());

  }


  @Test
  public void userAllRulesTest() {
    log.info("========> Running userAllRulesTest");

    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");

    List<String>userRules = new ArrayList<>();
    userRules.add("ETO");
    userRules.add("EON");
    userRules.add("ETA");

    Map<String, String> vo = new HashMap<String, String>(1);

    Map<String, String> result = ruleManager.executeEstimatedRule(userRules, vo);

  }

  @Test
  public void userARuleTest() {
    log.info("========> Running userARuleTest");

    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    RuleManager ruleManager = (RuleManager)context.getBean("ruleManager");

    Map<String, String> vo = new HashMap<String, String>(1);
    String ruleName = "ETO";
    Map<String, String> result =  ruleManager.executeEstimatedRule(ruleName, vo);
  }


  // Test Role with flight type and rules

  @Test
  public void adminRoleOneTest() {
    log.info("========> Running etaToeonTest");

    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    ContextManager contextManager = (ContextManager)context.getBean("contextManager");

    Map<String, List<String>> flightTypes = contextManager.getContextRole("admin_role_one");

    flightTypes.forEach(
        (flightType, rules) -> {
          if (rules.isEmpty()) {
            log.info("List is empty for flight type: " + flightType);
          } else {
            rules.forEach(rule->{
              log.info("Flight type: " + flightType + ".  Rule: " + rule);
            });
          }
        });
  }

// Test alerts based on event and role

  @Test
  public void eventAlertTest() {
    log.info("========> Running eventAlertTest");

    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    ContextManager contextManager = (ContextManager)context.getBean("contextManager");

    List<String> eventAlerts = contextManager.getEventAlert("eventEta");
    assertTrue(!eventAlerts.isEmpty());
    eventAlerts.forEach(eventAlert-> log.info("Event alert: "+ eventAlert));

    //TODO - the alerts for this event can be delegated to Foundation channel to send alert to target audience

  }

  @Test
  public void roleAlertTest() {
    log.info("========> Running roleAlertTest");

    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    ContextManager contextManager = (ContextManager)context.getBean("contextManager");

    List<String> roleAlerts = contextManager.getRoletAlert("roleAdmin");
    assertTrue(!roleAlerts.isEmpty());
    roleAlerts.forEach(roleAlert->log.info("Role alert: " + roleAlert));
    //TODO - the alerts for this role can be delegated to Foundation channel to send alert to target audience

  }

  @Test
  public void roleToEventAlertTest() {
    log.info("========> Running roleToEventAlertTest");

    ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("/"+
                    GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
                    GreetingConsumerStarterTest.class);

    ContextManager contextManager = (ContextManager)context.getBean("contextManager");
    List<String>alertEvents = contextManager.getAlertEventFromRole("roleAdmin");

    alertEvents.forEach(alertEvent->{
      List<String> eventAlerts = contextManager.getEventAlert(alertEvent);
      assertTrue(!eventAlerts.isEmpty());
      eventAlerts.forEach(eventAlert-> log.info("Admin Role: alert Event: + " + alertEvent + " Event alert: "+ eventAlert));
    });

  }




}
