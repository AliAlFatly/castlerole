package com.example.castlerole.service;


import com.example.castlerole.model.City;
import com.example.castlerole.model.User;
import com.example.castlerole.model.response.CityDataResponse;
import com.example.castlerole.model.response.UserDataResponse;
import com.example.castlerole.repository.CityRepository;
import com.example.castlerole.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.mockito.stubbing.Answer;
import org.springframework.test.context.ActiveProfiles;

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
        CityServiceUserDataMock.setX(CityServiceUserMock.getxCoordinate());
        CityServiceUserDataMock.setY(CityServiceUserMock.getyCoordinate());
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
        Mockito.when(cityRepository.findById(CityServiceUserMock.getId())).thenReturn(CityServiceCityMock);
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

    @Test
    public void getToopTipTest() {
        var cityC = cityServiceMock.getTooltipData("CityServiceUserMock", "Castle");
        Assert.assertEquals(16, cityC.getWood());
        Assert.assertEquals(16, cityC.getStone());
        Assert.assertEquals(16, cityC.getIron());
        Assert.assertEquals(16, cityC.getFood());
        var cityO = cityServiceMock.getTooltipData("CityServiceUserMock", "Oven");
        Assert.assertEquals(16, cityO.getWood());
        Assert.assertEquals(16, cityO.getStone());
        Assert.assertEquals(16, cityO.getIron());
        Assert.assertEquals(16, cityO.getFood());
        var cityM = cityServiceMock.getTooltipData("CityServiceUserMock", "Mine");
        Assert.assertEquals(16, cityM.getWood());
        Assert.assertEquals(16, cityM.getStone());
        Assert.assertEquals(16, cityM.getIron());
        Assert.assertEquals(16, cityM.getFood());
        var cityB = cityServiceMock.getTooltipData("CityServiceUserMock", "Barrack");
        Assert.assertEquals(16, cityB.getWood());
        Assert.assertEquals(16, cityB.getStone());
        Assert.assertEquals(16, cityB.getIron());
        Assert.assertEquals(16, cityB.getFood());
        var cityW = cityServiceMock.getTooltipData("CityServiceUserMock", "Woodwork");
        Assert.assertEquals(16, cityW.getWood());
        Assert.assertEquals(16, cityW.getStone());
        Assert.assertEquals(16, cityW.getIron());
        Assert.assertEquals(16, cityW.getFood());
        var cityF = cityServiceMock.getTooltipData("CityServiceUserMock", "Forgery");
        Assert.assertEquals(16, cityF.getWood());
        Assert.assertEquals(16, cityF.getStone());
        Assert.assertEquals(16, cityF.getIron());
        Assert.assertEquals(16, cityF.getFood());
    }
}
