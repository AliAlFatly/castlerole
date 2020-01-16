package com.example.Castlerole.service;

import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
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
        Assert.assertEquals(PointResult.getCoordinateX(),25);
        Assert.assertEquals(PointResult.getCoordinateY(), 25);
        Assert.assertEquals(PointResult.getType(), "Player");
    }

    @Test
    public void PointService_NodeTest() {
        var PointResult = pointService.getPointData(15, 15);
        Assert.assertEquals(PointResult.getCoordinateY(),15);
        Assert.assertEquals(PointResult.getCoordinateX(),15);
        Assert.assertEquals(PointResult.getType(), "forest");
    }

    @Test
    public void PointService_IsAttackableTest() {
        var PointResultUser = pointService.getPointData(25, 25);
        var PointResultNode = pointService.getPointData(15, 15);
        Assert.assertEquals(PointResultNode.isAttackable(), TRUE);
        Assert.assertEquals(PointResultUser.isAttackable(), TRUE);
    }

    @Test
    public void PointService_IsNotAttackableTest() {
        var PointResultEmpty = pointService.getPointData(10, 10);
        Assert.assertEquals(PointResultEmpty.isAttackable(), FALSE);
    }

}
