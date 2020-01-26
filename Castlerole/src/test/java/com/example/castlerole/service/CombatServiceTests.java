package com.example.castlerole.service;

import com.example.castlerole.config.JwtTokenUtil;
import com.example.castlerole.model.Node;
import com.example.castlerole.model.User;
import com.example.castlerole.model.helpertypes.Vector;
import com.example.castlerole.repository.NodeRepository;
import com.example.castlerole.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

//@ExtendWith(MockitoExtension.class)
public class CombatServiceTests {

    @BeforeEach
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
    @Mock
    private CityService cityService;

    @Test
    public void CombatAttack_UserTest() throws Exception {
        var Creturn = combatService.attack("attackuser",new Vector(25,25));
        System.out.println(
                "Wood won: " + Creturn.getWoodWon() + " " + "\n" +
                "Food won: " + Creturn.getFoodWon() + " " + "\n" +
                "Iron won: " + Creturn.getIronWon() + " " + "\n" +
                "Fight won: " + Creturn.isWon()
                );
    }
    @Test
    public void CombatAttack_NodeTest() throws Exception {
        var Creturn2 = combatService.attack("attackuser",new Vector(15,15));
        System.out.println(
                "Wood won: " + Creturn2.getWoodWon() + " " + "\n" +
                "Food won: " + Creturn2.getFoodWon() + " " + "\n" +
                "Iron won: " + Creturn2.getIronWon() + " " + "\n" +
                "Fight won: " + Creturn2.isWon()
        );
    }

}
