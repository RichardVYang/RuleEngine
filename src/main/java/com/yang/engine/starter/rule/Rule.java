package com.yang.engine.starter.rule;

import java.util.Map;

/**
 *
 * <p>Rule Engine quality attributes:
 * Extensibility, Maintainability, Understandability, Agility, Testability, Integrability, agnostic rule,
 * Configuration Driven, Open-closed Principle - Open for extension; closed for modification - adding new rules
 *
 * Ensure that a concrete class of this interface performs only a specific business rule. Delegates
 * functionalities such as connecting to database for CRUD, alerting stakeholders/other systems, and
 * connecting to external systems to other components using Foundation/Spring Integration Channels.
 *
 */


public interface Rule<K1, V1, K2, V2> {
    public Map<K2, V2> executeRule(Map<K1, V1> vo);
}
