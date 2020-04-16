package com.yang.engine.starter.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ContextManager {
  private Logger log = LoggerFactory.getLogger(this.getClass().getName());


  Map<String, Map<String, List<String>>> role;
  Map<String, List<String>>flightTypeRule;
  Map<String, List<String>> contextRule;
  Map<String, List<String>> eventAlert;
  Map<String, List<String>>roleAlert;
  Map<String, List<String>>roleToEvent;


  // Estimated times propagate through an orderly list. An ETD will update an ETO, EON and ETA.  An ETO will update an EON and ETA and an EON will update an ETA
  Map<String, List<String>>estimatedTime;


  /**
   *
   * @param eventName is the contextual event name
   * @return a list of cascaded rule names
   */

  public List<String> getEstimatedTimeRuleName(String eventName) {
    List<String> result = estimatedTime.get(eventName);
    result = (null != result) ? result : new ArrayList<String>();

    return result;
  }


  /**
   *
   * @param flightType is the flight type such as FERRY, CHARTER, and etc
   * @return a list of rule names associated with the flight type
   */
  public List<String> getFlightTypeContextRule(String flightType) {
    List<String>flightTypeContext = flightTypeRule.get(flightType);
    flightTypeContext = (null != flightTypeContext) ? flightTypeContext : new ArrayList<String>();

    return flightTypeContext;
  }

  /**
   * @param eventContext is the context of the event where it is originated. It could be an external
   *     system or a particular user. A context defines which rule, how many rule, and the order of
   *     selected rules to be executed.
   * @return an orderly list of rule names matching this event context
   */
  public List<String> getContext(String eventContext) {

    List<String> ruleContext = contextRule.get(eventContext);
    ruleContext = (null != ruleContext) ? ruleContext : new ArrayList<String>();

    return ruleContext;
  }

  public List<String> getCascadeContext(String estimatedTimeName) {
    List<String>list = estimatedTime.get(estimatedTimeName);
    list = (null != list) ? list : new ArrayList<String>();

    return list;
  }

  /**
   * NOTE: This is one possible solution.  There are other solutions; therefore, this solution may not be in use.  It is here for POC only
   * and is subject to remove later.
   *
   * @param roleName is role name of the admin.
   * @return a map collection of flight types with each flight type associates with a list of rule names that the admin role can update.
   *
   */

  public Map<String, List<String>> getContextRole(String roleName) {
     Map<String, List<String>> contextRole  = role.get(roleName);
    contextRole = (null != contextRole) ? contextRole : new HashMap<String, List<String>>();

     return contextRole;
    }

  /**
   *
   * @param eventName is the contextual event associates with alerts
   * @return A list of alerts that are associated with the given event
   */
  public List<String> getEventAlert(String eventName) {
      List<String>eventAlerts = eventAlert.get(eventName);
      eventAlerts = (null != eventAlerts) ? eventAlerts : new ArrayList<String>();
      return eventAlerts;
  }

  /**
   *
   * @param roleName is the security role associated with alerts
   * @return A list of alerts that are associated with the given role
   */
  public List<String> getRoletAlert(String roleName) {
    List<String>roleAlerts = roleAlert.get(roleName);
    roleAlerts = (null != roleAlerts) ? roleAlerts : new ArrayList<String>();
    return roleAlerts;
  }

  public List<String> getAlertEventFromRole(String roleName) {
    List<String>alertEvents = roleToEvent.get(roleName);
    alertEvents = (null != alertEvents)?alertEvents:new ArrayList<String>();
    return alertEvents;
  }





  // getters and setters
  public Map<String, List<String>> getContextRule() {
    return contextRule;
  }

  public void setContextRule(Map<String, List<String>> contextRule) {
    this.contextRule = contextRule;
  }

  public Map<String, List<String>> getEstimatedTime() {
    return estimatedTime;
  }

  public void setEstimatedTime(Map<String, List<String>> estimatedTime) {
    this.estimatedTime = estimatedTime;
  }

  public Map<String, Map<String, List<String>>> getRole() {
    return role;
  }

  public void setRole(Map<String, Map<String, List<String>>> role) { this.role = role; }

  public Map<String, List<String>> getEventAlert() {
    return eventAlert;
  }

  public void setEventAlert(Map<String, List<String>> eventAlert) {
    this.eventAlert = eventAlert;
  }

  public Map<String, List<String>> getRoleAlert() {
    return roleAlert;
  }

  public void setRoleAlert(Map<String, List<String>> roleAlert) {
    this.roleAlert = roleAlert;
  }

  public Map<String, List<String>> getRoleToEvent() { return roleToEvent;
  }

  public void setRoleToEvent(Map<String, List<String>> roleToEvent) { this.roleToEvent = roleToEvent;
  }



}
