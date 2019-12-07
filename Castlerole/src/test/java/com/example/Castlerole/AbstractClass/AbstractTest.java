package com.example.Castlerole.AbstractClass;

import com.example.Castlerole.config.JwtFilter;
import com.example.Castlerole.controller.GameController;
import com.example.Castlerole.controller.JwtAuthenticationController;

import com.example.Castlerole.model.dto.UserDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class AbstractTest {

    protected MockMvc mvc;

    @Autowired
    private JwtFilter jwtFilter;

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

        this.mvc = MockMvcBuilders
                .standaloneSetup(gameController)
                //.addFilter(jwtFilter)
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
