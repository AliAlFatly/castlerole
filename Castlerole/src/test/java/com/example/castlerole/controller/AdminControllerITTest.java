package com.example.castlerole.controller;

import com.example.castlerole.Config.ControllerTestConfig;
import com.example.castlerole.Config.StopWatchConfig;
import com.example.castlerole.repository.NodeRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerITTest extends ControllerTestConfig {

    @Autowired
    private NodeRepository nodeRepository;

    @Rule
    public StopWatchConfig stopwatch = new StopWatchConfig();

    private final String secretKey = "secretcodehere";

    @Override
    @Before
    public void setUpAdminControl() {
        super.setUpAdminControl();

    }

    @Test
    public void getNodesAmountTest() throws Exception {

        String uri = "/addNodes/";
        int amount = 100;

        //long delta = 30;
        //Thread.sleep(300l);
        //System.out.println(stopwatch.runtime(TimeUnit.MILLISECONDS));
        long BeginTime = stopwatch.runtime(TimeUnit.MILLISECONDS);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + amount + "/" + secretKey)
                .contentType(MediaType.APPLICATION_JSON)

                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        long EndTime = stopwatch.runtime(TimeUnit.MILLISECONDS);
        //System.out.println(stopwatch.runtime(TimeUnit.MILLISECONDS));
        System.out.println(nodeRepository.count());
        System.out.println("Application took: " + (EndTime - BeginTime) + " Milliseconds to GET");
        assertEquals(nodeRepository.count(), 200);


    }

}
