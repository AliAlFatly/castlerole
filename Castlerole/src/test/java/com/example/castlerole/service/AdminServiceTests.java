package com.example.castlerole.service;

import com.example.castlerole.model.helpertypes.IntVector;
import com.example.castlerole.repository.NodeRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

//@ExtendWith(MockitoExtension.class)
//@ActiveProfiles("test")
//@RunWith()
//@SpringBootTest
public class AdminServiceTests {

    @BeforeEach
    public void setUp() throws Exception {
        adminService = new AdminService();
        MockitoAnnotations.initMocks(this);
        Mockito.when(userService.getXY())
                .thenReturn(new IntVector(25,25));
    }

    @InjectMocks
    private AdminService adminService;
    @Mock
    private NodeRepository nodeRepository;
    @Mock
    private JwtUserService userService;

    @Test
    public void whenValid_shouldReturnNodes() throws Exception {
        int amount = 10;
        String answer = adminService.generateNodes(1);
        Assert.assertEquals(answer, "done");
        System.out.println(answer);
        //String answer = adminService.generateNodes(amount);



    }


}
