package com.example.Castlerole.service;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.helpertypes.IntVector;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.Context;

import java.beans.BeanProperty;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {

    @Before
    public void setUp() throws Exception {
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