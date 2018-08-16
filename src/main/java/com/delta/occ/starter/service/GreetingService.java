package com.delta.occ.starter.service;

import com.delta.occ.starter.pojo.Greeting;

/**
 *  Best practice and easier for TDD and Mock object for testing purpose
 */

public interface GreetingService {

    public void processGreeting(Greeting greeting);
}
