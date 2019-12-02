package com.example.Castlerole.AbstractClass;

import com.example.Castlerole.config.JwtFilter;
import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.controller.JwtAuthenticationController;
import com.example.Castlerole.service.JwtAuthenticationService;
import com.example.Castlerole.service.JwtUserService;
import com.example.Castlerole.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        JwtAuthenticationController.class
})

@WebAppConfiguration
@MockBeans(value = {

        @MockBean(JwtAuthenticationService.class),

} )

public class AbstractTest {



    protected MockMvc mvc;

    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;

//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    @MockBean
//    private JwtFilter jwtFilter;



    protected void setUp() {
        MockitoAnnotations.initMocks(this);


        //ReflectionTestUtils.setField(jwtUserService, "userRepository", userRepository);
        this.mvc = MockMvcBuilders.standaloneSetup(jwtAuthenticationController)
                //.apply(springSecurity(jwtFilter))
                .build();

    }



    protected String mapToJson(Object obj) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
        throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }


}
