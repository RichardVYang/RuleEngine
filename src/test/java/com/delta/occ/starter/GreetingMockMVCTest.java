package com.delta.occ.starter;


import com.delta.occ.starter.controller.GreetingController;
import com.delta.occ.starter.pojo.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(locations = {"/GreetingConsumerStarterContext.xml"})
public class GreetingMockMVCTest {

    private static final Logger logger = LoggerFactory.getLogger(GreetingMockMVCTest.class);
    private static final String POST_URL = "/starter/v1/greeting/setgreeting";
    private static final String GET_URL = "/starter/v1/greeting/getgreeting?id=1";

    @Autowired
    private MockMvc mvc;

    @MockBean
    GreetingController greetingController;

    public MockHttpServletRequestBuilder postBuilder(String url) throws Exception {
        return post(url).accept("application/json").contentType("application/json");
    }

    public MockHttpServletRequestBuilder getBuilder(String url) throws Exception {
        return get(url).accept("application/json").contentType("application/json");
    }

    @Test
    public void getGreetingTest() throws Exception {
        logger.info("****** Testing: getGreetingTest()...");

        Greeting greeting = new Greeting(1L, "James Gosling");

        given(greetingController.getGreeting(Long.toString(greeting.getId()))).willReturn(greeting);


        ResultActions result = this.mvc.perform(get("/starter/v1/greeting/getgreeting?id=" + greeting.getId()));
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(greeting.getMessage()));

        /*
        this.mvc.perform(get("/starter/v1/greeting/getgreeting?id=" + greeting.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(greeting.getMessage()));
        */


      //  mvc.perform(get(GET_URL)).andExpect(jsonPath("$.message").value(greeting.getMessage()));

    }


    @Test
    public void postGreetingTest() throws Exception {
        logger.info("****** Testing: postGreetingTest()...");

        Greeting greeting = new Greeting(1L, "James Gosling");
        given(greetingController.setGreeting(greeting)).willReturn(greeting);

        MockHttpServletRequestBuilder request = post(POST_URL);
        request.content(new ObjectMapper().writeValueAsString(greeting));
        request.accept(MediaType.APPLICATION_JSON);
        request.contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());

     //   mvc.perform(post(POST_URL)
     //           .contentType(MediaType.APPLICATION_JSON)
     //           .content(new ObjectMapper().writeValueAsString(greeting)))
     //           .andExpect(status().isOk())
     //           .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }

}
