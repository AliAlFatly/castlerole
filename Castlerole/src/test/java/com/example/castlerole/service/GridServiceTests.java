package com.example.castlerole.service;

import com.example.castlerole.model.Node;
import com.example.castlerole.model.User;
import com.example.castlerole.model.dto.UserDTO;
import com.example.castlerole.model.response.GridResponse;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(classes = GridService.class)
public class GridServiceTests {

    @MockBean
    private NodeRepository nodeRepository;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private GridService gridService;
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        User user1 = new User();
        user1.setCoordinateY(5);
        user1.setCoordinateX(5);
        user1.setPictureReference("player");
        User user2 = new User();
        user2.setCoordinateY(10);
        user2.setCoordinateX(10);
        user2.setPictureReference("player");
        User user3 = new User();
        user3.setCoordinateY(15);
        user3.setCoordinateX(15);
        user3.setPictureReference("player");
        Node node1 = new Node();
        node1.setYCoordinate(6);
        node1.setXCoordinate(6);
        node1.setPictureReference("forest");
        Node node2 = new Node();
        node2.setYCoordinate(4);
        node2.setXCoordinate(4);
        node2.setPictureReference("lake");
        Node node3 = new Node();
        node3.setYCoordinate(7);
        node3.setXCoordinate(7);
        node3.setPictureReference("mountain");
        var nodeArray = new ArrayList<Node>();
        nodeArray.add(node1);
        nodeArray.add(node2);
        nodeArray.add(node3);
        var userArray = new ArrayList<User>();
        userArray.add(user1);
        userArray.add(user2);
        userArray.add(user3);
        Mockito.when(userRepository.getUsersInGrid(anyInt(),anyInt(),anyInt(),anyInt())).thenReturn(Optional.of(userArray));
        Mockito.when(nodeRepository.getNodesInGrid(anyInt(),anyInt(),anyInt(),anyInt())).thenReturn(Optional.of(nodeArray));
    }

    @Test
    public void GridService_Test() {

        List<GridResponse> gridreturn = gridService.getGrid(25, 25);
        Assert.assertEquals(gridreturn.iterator().next().getPicture(),"player");
        Assert.assertEquals(gridreturn.iterator().next().getX(),5);
        Assert.assertEquals(gridreturn.iterator().next().getY(),5);


    }

}
