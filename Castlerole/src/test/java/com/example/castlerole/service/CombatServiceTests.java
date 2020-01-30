package com.example.castlerole.service;

import com.example.castlerole.config.JwtTokenUtil;
import com.example.castlerole.model.City;
import com.example.castlerole.model.Node;
import com.example.castlerole.model.User;
import com.example.castlerole.model.helpertypes.Vector;
import com.example.castlerole.model.response.CityDataResponse;
import com.example.castlerole.repository.CityRepository;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

//@ExtendWith(MockitoExtension.class)
public class CombatServiceTests {

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        var attackuser = new User();
        attackuser.setUsername("attackuser");
        attackuser.setPassword("password");
        attackuser.setStone(3000);
        attackuser.setWood(3000);
        attackuser.setFood(3000);
        attackuser.setIron(3000);
        attackuser.setCoordinateY(25);
        attackuser.setCoordinateX(25);
        attackuser.setTroops(300);
        attackuser.setId(1);
        var attackusercity = new City();
        attackusercity.setUser(attackuser);
        attackusercity.setId(1);
        attackusercity.setBarracksLevel(1);
        attackusercity.setCasteLevel(1);
        attackusercity.setForgeryLevel(1);
        attackusercity.setMineLevel(1);
        attackusercity.setOvenLevel(1);
        attackusercity.setWoodworksLevel(1);
        var defenduser = new User();
        defenduser.setUsername("defenduser");
        defenduser.setPassword("password");
        defenduser.setStone(300);
        defenduser.setWood(300);
        defenduser.setFood(300);
        defenduser.setIron(300);
        defenduser.setCoordinateY(20);
        defenduser.setCoordinateX(20);
        defenduser.setTroops(6000);
        defenduser.setId(1);
        var defendusercity = new City();
        defendusercity.setUser(defenduser);
        defendusercity.setId(2);
        defendusercity.setBarracksLevel(1);
        defendusercity.setCasteLevel(1);
        defendusercity.setForgeryLevel(1);
        defendusercity.setMineLevel(1);
        defendusercity.setOvenLevel(1);
        defendusercity.setWoodworksLevel(1);
        var defendnode = new Node();
        defendnode.setYCoordinate(15);
        defendnode.setXCoordinate(15);
        defendnode.setTroops(100);
        defendnode.setType("forest");
        defendnode.setYieldMax(300);
        defendnode.setYieldMin(150);
        Mockito.when(userRepository.findByUsername(any()))
                .thenReturn(defenduser);
        Mockito.when(userRepository.findByCoordinateXAndCoordinateY(
                attackuser.getxCoordinate(),
                attackuser.getyCoordinate()))
                .thenReturn(Optional.of(attackuser));
        Mockito.when(nodeRepository.findByCoordinateXAndCoordinateY(
                defendnode.getxCoordinate(),
                defendnode.getyCoordinate()))
                .thenReturn(Optional.of(defendnode));
        Mockito.when(cityService.getCityData(anyString()))
                .thenReturn(new CityDataResponse(
                        "defenduser",
                        1,
                        1,
                        1,
                        1,
                        1,
                        1
                        ));
    }
    @Mock
    private NodeRepository nodeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @InjectMocks
    private CombatService combatService;
    @Mock
    private CityService cityService;

    @Test
    public void CombatAttack_UserTest() throws Exception {
        var Creturn = combatService.attack("attackuser",new Vector(25,25));
        System.out.println(
                "CombatAttack User Test: " + "\n" +
                "Wood won: " + Creturn.getWoodWon() + " " + "\n" +
                "Food won: " + Creturn.getFoodWon() + " " + "\n" +
                "Iron won: " + Creturn.getIronWon() + " " + "\n" +
                "Fight won: " + Creturn.isWon()
                );
        Assert.assertEquals(100, Creturn.getFoodWon());
        Assert.assertEquals(100, Creturn.getIronWon());
        Assert.assertEquals(100, Creturn.getWoodWon());
        Assert.assertEquals(100, Creturn.getStoneWon());
    }
    @Test
    public void CombatAttack_NodeTest() throws Exception {
        var Creturn2 = combatService.attack("attackuser",new Vector(15,15));
        System.out.println(
                "CombatAttack Node Test: " + "\n" +
                "Wood won: " + Creturn2.getWoodWon() + " " + "\n" +
                "Food won: " + Creturn2.getFoodWon() + " " + "\n" +
                "Iron won: " + Creturn2.getIronWon() + " " + "\n" +
                "Fight won: " + Creturn2.isWon()
        );
        Assert.assertEquals(0, Creturn2.getStoneWon());
        //Assert.assertEquals(Creturn2.getWoodWon(), 0);
        Assert.assertEquals(0, Creturn2.getIronWon());
        Assert.assertEquals(0, Creturn2.getFoodWon());
    }

}
