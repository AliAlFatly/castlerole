package com.example.Castlerole.service;

import com.example.Castlerole.Config.JpaConfig;
import com.example.Castlerole.model.City;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.response.CityDataResponse;
import com.example.Castlerole.model.response.UserDataResponse;
import com.example.Castlerole.repository.CityRepository;
import com.example.Castlerole.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.mapping.Any;
import org.hibernate.mapping.Array;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.Convert;
import java.io.DataInput;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;

//@ActiveProfiles("test")
//@SpringJUnitConfig
//@SpringBootTest
@ActiveProfiles("test")
public class CityServiceTests {

    @Mock
    private CityRepository cityRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;
//    @Autowired
//    private JwtUserService jwtUserService;

    @InjectMocks
    private CityService cityServiceMock;

    @BeforeEach
    private void setUpCityServiceTests() throws Exception {
        MockitoAnnotations.initMocks(this);
        var CityServiceUserMock = new User();
        CityServiceUserMock.setId(1);
        CityServiceUserMock.setUsername("CityServiceUserMock");
        CityServiceUserMock.setPassword("password");
        CityServiceUserMock.setStone(300);
        CityServiceUserMock.setWood(300);
        CityServiceUserMock.setFood(300);
        CityServiceUserMock.setIron(300);
        CityServiceUserMock.setCoordinateY(25);
        CityServiceUserMock.setCoordinateX(25);
        CityServiceUserMock.setTroops(300);

        var CityServiceUserDataMock = new UserDataResponse();
        CityServiceUserDataMock.setCoordinateX(CityServiceUserMock.getxCoordinate());
        CityServiceUserDataMock.setCoordinateY(CityServiceUserMock.getyCoordinate());
        CityServiceUserDataMock.setFood(CityServiceUserMock.getFood());
        CityServiceUserDataMock.setStone(CityServiceUserMock.getStone());
        CityServiceUserDataMock.setTroops(CityServiceUserMock.getTroops());
        CityServiceUserDataMock.setUsername(CityServiceUserMock.getUsername());
        CityServiceUserDataMock.setWood(CityServiceUserMock.getWood());
        CityServiceUserDataMock.setIron(CityServiceUserMock.getIron());

        var CityServiceCityMock = new City();
        CityServiceCityMock.setId(1);
        CityServiceCityMock.setUser(CityServiceUserMock);
        CityServiceCityMock.setBarracksLevel(1);
        CityServiceCityMock.setCasteLevel(1);
        CityServiceCityMock.setForgeryLevel(1);
        CityServiceCityMock.setMineLevel(1);
        CityServiceCityMock.setOvenLevel(1);
        CityServiceCityMock.setWoodworksLevel(1);

        Mockito.when(userRepository.findByUsername("CityServiceUserMock")).thenReturn(CityServiceUserMock);
        Mockito.when(cityRepository.findByid(CityServiceUserMock.getId())).thenReturn(CityServiceCityMock);
        Mockito.when(userService.getUserData("CityServiceUserMock")).thenReturn(CityServiceUserDataMock);





    }

    @Test
    public void GetCityData_UnitTest() throws Exception {
        CityDataResponse shouldBe = new CityDataResponse(
                "CityServiceUserMock",
                1,
                1,
                1,
                1,
                1,
                1
                );
        CityDataResponse data =  cityServiceMock.getCityData("CityServiceUserMock");
        Assertions.assertEquals(
                new ObjectMapper().writeValueAsString(data),
                new ObjectMapper().writeValueAsString(shouldBe)
        );
    }
    @Test
    public void UpdateCity_UnitTest() throws Exception {
        Mockito.when(cityRepository.updateCastleLevel(anyInt(),anyLong()))
                .thenAnswer((Answer<Integer>) invocation -> {
                    Object[] args = invocation.getArguments();
                    //Object mock = invocation.getMock
                    Assertions.assertEquals(2,args[0]);
                    return (Integer) args[0];
        });
        Mockito.when(cityRepository.updateBarracksLevel(anyInt(),anyLong()))
                .thenAnswer((Answer<Integer>) invocation -> {
                    Object[] args = invocation.getArguments();
                    //Object mock = invocation.getMock
                    Assertions.assertEquals(2,args[0]);
                    return (Integer) args[0];
                });
        Mockito.when(cityRepository.updateForgeryLevel(anyInt(),anyLong()))
                .thenAnswer((Answer<Integer>) invocation -> {
                    Object[] args = invocation.getArguments();
                    //Object mock = invocation.getMock
                    Assertions.assertEquals(2,args[0]);
                    return (Integer) args[0];
                });
        Mockito.when(cityRepository.updateMineLevel(anyInt(),anyLong()))
                .thenAnswer((Answer<Integer>) invocation -> {
                    Object[] args = invocation.getArguments();
                    //Object mock = invocation.getMock
                    Assertions.assertEquals(2,args[0]);
                    return (Integer) args[0];
                });
        Mockito.when(cityRepository.updateOvensLevel(anyInt(),anyLong()))
                .thenAnswer((Answer<Integer>) invocation -> {
                    Object[] args = invocation.getArguments();
                    //Object mock = invocation.getMock
                    Assertions.assertEquals(2,args[0]);
                    return (Integer) args[0];
                });
        Mockito.when(cityRepository.updateWoodworksLevel(anyInt(),anyLong()))
                .thenAnswer((Answer<Integer>) invocation -> {
                    Object[] args = invocation.getArguments();
                    //Object mock = invocation.getMock
                    Assertions.assertEquals(2,args[0]);
                    return (Integer) args[0];
                });

        cityServiceMock.updateBuilding("CityServiceUserMock", "Castle");
        cityServiceMock.updateBuilding("CityServiceUserMock", "Barack");
        cityServiceMock.updateBuilding("CityServiceUserMock", "Forgery");
        cityServiceMock.updateBuilding("CityServiceUserMock", "Mine");
        cityServiceMock.updateBuilding("CityServiceUserMock", "Oven");
        cityServiceMock.updateBuilding("CityServiceUserMock", "Woodworks");
        //var data2 = cityRepository.findByid(1);

        //System.out.println(data2.getCastleLevel());
    }
}
