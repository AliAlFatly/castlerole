package com.example.castlerole.service;

import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
