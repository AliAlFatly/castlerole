package com.example.castlerole.service;

import com.example.castlerole.model.helpertypes.IntVector;
import com.example.castlerole.repository.NodeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;

//@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("test")
//@RunWith()
//@SpringBootTest
public class AdminServiceTests {

    @InjectMocks
    private AdminService adminService;
    @Mock
    private NodeRepository nodeRepository;
    @Mock
    private JwtUserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        adminService = new AdminService();
        MockitoAnnotations.initMocks(this);
        Mockito.when(userService.getXY())
                .thenReturn(new IntVector(25,25));
        //Mockito.when(nodeRepository.save()).thenReturn();
    }
//    @Test
//    public void whenValid_shouldReturnNodes() throws Exception {
//        int amount = 10;
//        String answer = adminService.generateNodes(amount);
//        Assert.assertEquals(answer, "done");
//        System.out.println(answer);
//        //String answer = adminService.generateNodes(amount);
//
//
//
//    }


}
