package com.example.castlerole.service;

import com.example.castlerole.model.Node;
import com.example.castlerole.model.User;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;
import org.junit.Assert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
public class PointServiceTests {

    @Mock
    private NodeRepository nodeRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PointService pointService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        var PlayerPoint = new User();
        PlayerPoint.setUsername("attackuser");
        PlayerPoint.setPassword("password");
        PlayerPoint.setStone(300);
        PlayerPoint.setWood(300);
        PlayerPoint.setFood(300);
        PlayerPoint.setCoordinateY(25);
        PlayerPoint.setCoordinateX(25);
        PlayerPoint.setTroops(300);
        var NodePoint = new Node();
        NodePoint.setYCoordinate(15);
        NodePoint.setXCoordinate(15);
        NodePoint.setTroops(300);
        NodePoint.setType("forest");
        NodePoint.setYieldMax(300);
        NodePoint.setYieldMin(150);
        Mockito.when(userRepository.findByCoordinateXAndCoordinateY(25, 25))
                .thenReturn(java.util.Optional.of(PlayerPoint));
        Mockito.when(nodeRepository.findByCoordinateXAndCoordinateY(15,15))
                .thenReturn(java.util.Optional.of(NodePoint));

//        Mockito.doReturn(java.util.Optional.of(PlayerPoint))
//                .when(userRepository)
//                        .findByCoordinateXAndCoordinateY(25,25);
//        Mockito.doReturn(java.util.Optional.of(NodePoint))
//                .when(nodeRepository)
//                        .findByCoordinateXAndCoordinateY(15,15);
    }

    @Test
    public void PointService_UserTest() {
        var PointResult = pointService.getPointData(25,25);
        Assert.assertEquals(25,PointResult.getCoordinateX());
        Assert.assertEquals(25, PointResult.getCoordinateY());
        Assert.assertEquals("Player", PointResult.getType());
    }

    @Test
    public void PointService_NodeTest() {
        var PointResult = pointService.getPointData(15, 15);
        Assert.assertEquals(15,PointResult.getCoordinateY());
        Assert.assertEquals(15,PointResult.getCoordinateX());
        Assert.assertEquals("forest", PointResult.getType() );
    }

    @Test
    public void PointService_IsAttackableTest() {
        var PointResultUser = pointService.getPointData(25, 25);
        var PointResultNode = pointService.getPointData(15, 15);
        Assert.assertEquals(TRUE, PointResultNode.isAttackable());
        Assert.assertEquals(TRUE, PointResultUser.isAttackable());
    }

    @Test
    public void PointService_IsNotAttackableTest() {
        var PointResultEmpty = pointService.getPointData(10, 10);
        Assert.assertEquals(FALSE, PointResultEmpty.isAttackable());
    }

}
