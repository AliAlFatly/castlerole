package com.example.Castlerole.service;

import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.model.Node;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.helpertypes.IntVector;
import com.example.Castlerole.model.helpertypes.Vector;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class CombatServiceTests {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        var attackuser = new User();
        attackuser.setUsername("attackuser");
        attackuser.setPassword("password");
        attackuser.setStone(300);
        attackuser.setWood(300);
        attackuser.setFood(300);
        attackuser.setCoordinateY(25);
        attackuser.setCoordinateX(25);
        attackuser.setTroops(300);
        var defenduser = new User();
        defenduser.setUsername("defenduser");
        defenduser.setPassword("password");
        defenduser.setStone(300);
        defenduser.setWood(300);
        defenduser.setFood(300);
        defenduser.setCoordinateY(20);
        defenduser.setCoordinateX(20);
        defenduser.setTroops(600);
        var defendnode = new Node();
        defendnode.setYCoordinate(15);
        defendnode.setXCoordinate(15);
        defendnode.setTroops(300);
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
    }
    @Mock
    private NodeRepository nodeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtTokenUtil jwtTokenUtil;
    @InjectMocks
    private CombatService combatService;

    @Test
    public String CombatAttack_UserTest() {
        var Creturn = combatService.attack("attackuser",new Vector(25,25));
        System.out.println(
                "Wood won: " + Creturn.getWoodWon() + " " + "\n" +
                "Food won: " + Creturn.getFoodWon() + " " + "\n" +
                "Iron won: " + Creturn.getIronWon() + " " + "\n" +
                "Fight won: " + Creturn.isWon()
                );
        return "Done";
    };
    @Test
    public String CombatAttack_NodeTest() {
        var Creturn2 = combatService.attack("attackuser",new Vector(15,15));
        System.out.println(
                "Wood won: " + Creturn2.getWoodWon() + " " + "\n" +
                "Food won: " + Creturn2.getFoodWon() + " " + "\n" +
                "Iron won: " + Creturn2.getIronWon() + " " + "\n" +
                "Fight won: " + Creturn2.isWon()
        );
        return "Done";
    }

}
