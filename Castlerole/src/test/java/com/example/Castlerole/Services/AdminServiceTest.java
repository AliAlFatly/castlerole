package com.example.Castlerole.Services;

import com.example.Castlerole.Config.ControllerTestConfig;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceTest extends ControllerTestConfig {

    @Autowired
    private AdminService adminService;

    @Autowired
    private NodeRepository nodeRepository;

    @Test
    public void adminServiceTest() throws Exception {

        int amount = 100;

        String[] types = {"forest", "lake", "mountain", "mine"};

        adminService.generateNodes(amount);

        Assert.assertEquals(nodeRepository.count(), amount);

        Iterable nodes = nodeRepository.findAll();
        String nodesS = super.mapToJson(nodeRepository.findAll());


    }

}
