package com.example.Castlerole.AbstractClass;

import com.example.Castlerole.Config.JpaConfig;
import com.example.Castlerole.config.JwtFilter;
import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.controller.GameController;
import com.example.Castlerole.controller.JwtAuthenticationController;
import com.example.Castlerole.repository.NodeRepository;
import com.example.Castlerole.repository.UserRepository;
import com.example.Castlerole.service.JwtAuthenticationService;
import com.example.Castlerole.service.JwtUserService;
import com.example.Castlerole.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class AbstractTest {

    protected MockMvc mvc;

    @Autowired
    private GameController gameController;

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;



    protected void setUpJwtAuth() {
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders
                .standaloneSetup(jwtAuthenticationController)
                .build();

    }
    protected void setUpGameControl() {
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders.standaloneSetup(gameController)
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
