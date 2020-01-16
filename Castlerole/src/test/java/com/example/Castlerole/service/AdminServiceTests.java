package com.example.Castlerole.service;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.helpertypes.IntVector;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.apache.maven.surefire.testset.TestRequest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("test")
//@RunWith()
//@SpringBootTest
public class AdminServiceTests {

    @BeforeEach
    public void setUp() throws Exception {
        adminService = new AdminService();
        MockitoAnnotations.initMocks(this);
        Mockito.when(userService.getXY())
                .thenReturn(new IntVector(25,25));
    }

    @InjectMocks
    private AdminService adminService;
    @Mock
    private NodeRepository nodeRepository;
    @Mock
    private JwtUserService userService;

    @Test
    public void whenValid_shouldReturnNodes() throws Exception {
        int amount = 10;
        String answer = adminService.generateNodes(1);
        Assert.assertEquals(answer, "done");
        System.out.println(answer);
        //String answer = adminService.generateNodes(amount);



    }


}
