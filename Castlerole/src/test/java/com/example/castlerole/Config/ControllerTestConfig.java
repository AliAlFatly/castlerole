package com.example.castlerole.Config;

import com.example.castlerole.controller.AdminController;
import com.example.castlerole.controller.GameController;
import com.example.castlerole.controller.JwtAuthenticationController;

import com.example.castlerole.model.dto.UserDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static junit.framework.TestCase.fail;
import static org.junit.Assume.assumeTrue;


//@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class ControllerTestConfig {

    protected MockMvc mvc;

    @Autowired
    private GameController gameController;

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;

    @Autowired
    private AdminController adminController;



    protected void setUpJwtAuth() {
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders
                .standaloneSetup(jwtAuthenticationController)
                .build();

    }
    protected void setUpGameControl() {
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders
                .standaloneSetup(gameController)
                //.addFilter(jwtFilter)
                .build();
    }
    protected void setUpAdminControl() {
        MockitoAnnotations.initMocks(adminController);

        this.mvc = MockMvcBuilders
                .standaloneSetup(adminController)
                .build();
    }

    protected UserDTO createDTO(String username, String password) {
        UserDTO dto = new UserDTO();

        dto.setUsername(username);
        dto.setPassword(password);

        return dto;
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
