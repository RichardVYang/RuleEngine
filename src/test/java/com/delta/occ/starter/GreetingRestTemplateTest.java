package com.delta.occ.starter;


import com.delta.occ.starter.pojo.Greeting;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:GreetingConsumerStarterTestContext.xml"})
public class GreetingRestTemplateTest {
    private static final Logger log = LoggerFactory.getLogger(GreetingRestTemplateTest.class);

    private static final String GET_URL = "http://localhost:8080/starter/v1/greeting/person?name=James";
    private static final String POST_URL = "http://localhost:8080/starter/v1/greeting//setgreeting";

    private HttpHeaders buildHeadersForGet() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }

    @Test
    public void testGetWithParam() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Integer> entity = new HttpEntity<>(buildHeadersForGet());
        ResponseEntity<Greeting> response =
                restTemplate.exchange(GET_URL, HttpMethod.GET, entity, Greeting.class);

        assertTrue(response.getBody().getMessage().trim().length() > 0);
    }

    @Test
    public void httpGatewayPostShipUnAssignmentTest() {
        String greetingMsg = "James Gosling";
        Greeting greeting = new Greeting(1L, greetingMsg);

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");

        HttpEntity<Object> entity = new HttpEntity<Object>(greeting, headers);

        ResponseEntity<Greeting> response = restTemplate.exchange(POST_URL, HttpMethod.POST, entity, Greeting.class);

        Greeting body = response.getBody();

        assertEquals(greetingMsg, response.getBody().getMessage());

    }



}
