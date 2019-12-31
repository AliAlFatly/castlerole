package com.example.Castlerole.Services;

import com.example.Castlerole.Config.ControllerTestConfig;
import com.example.Castlerole.model.helpertypes.IntVector;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.service.AdminService;
import com.example.Castlerole.service.JwtUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private NodeRepository nodeRepository;

    @MockBean
    private JwtUserService userService;

    @Test
    public void adminServiceTest() throws Exception {

        when(userService.getXY()).thenReturn(new IntVector(23,25));

        int amount = 100;


        String[] types = {"forest", "lake", "mountain", "mine"};

        var tekstt = adminService.generateNodes(amount);

        //Assert.assertEquals(nodeRepository.count(), amount);

        System.out.println(tekstt.toString());
        Iterable nodes = nodeRepository.findAll();

        //String nodesS = super.mapToJson(nodeRepository.findAll());
    Assert.assertEquals(tekstt,"done");

    }

}
