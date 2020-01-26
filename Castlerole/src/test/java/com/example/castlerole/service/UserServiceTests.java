package com.example.castlerole.service;

import com.example.castlerole.config.JwtTokenUtil;
import com.example.castlerole.model.User;
import com.example.castlerole.model.response.UserDataResponse;
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
import static org.mockito.ArgumentMatchers.any;

//@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        var UserServiceMock = new User();
        UserServiceMock.setUsername("UserServiceMock");
        UserServiceMock.setPassword("password");
        UserServiceMock.setStone(300);
        UserServiceMock.setWood(300);
        UserServiceMock.setFood(300);
        UserServiceMock.setCoordinateY(25);
        UserServiceMock.setCoordinateX(25);
        UserServiceMock.setTroops(300);
        Mockito.when(userRepository.findByUsername("UserServiceMock")).thenReturn(UserServiceMock);

    }

    @Test
    public void UserService_ExistTest() throws Exception {
        var ExistResult = userService.UserExist("UserServiceMock");
        Assert.assertEquals(TRUE,ExistResult);
    }
    @Test
    public void UserService_DoesntExistTest() throws Exception {
        var DoesntResult = userService.UserExist("DoesntExist");
        Assert.assertEquals(FALSE, DoesntResult);
    }
    @Test
    public void UserService_getCoordsTest() throws Exception {
        var CoordResult = userService.getUserCoordinates("UserServiceMock");
        Assert.assertEquals(CoordResult.getX(), 25);
        Assert.assertEquals(CoordResult.getY(), 25);
    }
    @Test
    public void UserService_getDataTest() throws Exception {
        var DataResult = userService.getUserData("UserServiceMock");
        var UserServiceMock = new UserDataResponse();
        UserServiceMock.setUsername("UserServiceMock");
        UserServiceMock.setStone(300);
        UserServiceMock.setWood(300);
        UserServiceMock.setFood(300);
        UserServiceMock.setCoordinateY(25);
        UserServiceMock.setCoordinateX(25);
        UserServiceMock.setTroops(300);
        Assert.assertEquals(DataResult.getCoordinateX(),UserServiceMock.getCoordinateX());
        Assert.assertEquals(DataResult.getCoordinateY(),UserServiceMock.getCoordinateY());
        Assert.assertEquals(DataResult.getFood(),UserServiceMock.getFood());
        Assert.assertEquals(DataResult.getIron(),UserServiceMock.getIron());
        Assert.assertEquals(DataResult.getStone(),UserServiceMock.getStone());
        Assert.assertEquals(DataResult.getTroops(),UserServiceMock.getTroops());
        Assert.assertEquals(DataResult.getUsername(),UserServiceMock.getUsername());
        Assert.assertEquals(DataResult.getWood(),UserServiceMock.getWood());
    }



}
