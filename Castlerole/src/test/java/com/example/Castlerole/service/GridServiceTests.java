package com.example.Castlerole.service;

import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

//@ExtendWith(MockitoExtension.class)
public class GridServiceTests {

    @Mock
    private NodeRepository nodeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GridService gridService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gridService.setX(50);
        gridService.setY(50);
        gridService.left(0);
        gridService.right(50);
        gridService.bottom(0);
        gridService.top(50);

    }

    @Test
    public void GridService_Test() {

        var gridreturn = gridService.getGrid(25, 25);
        System.out.println(
                gridreturn.size() + "\n" +
                        Arrays.toString(gridreturn.toArray()).indexOf(3)
        );
    }

}
