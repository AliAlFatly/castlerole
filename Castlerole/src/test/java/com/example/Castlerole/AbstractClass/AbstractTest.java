package com.example.Castlerole.AbstractClass;

import com.example.Castlerole.Config.JpaConfig;
import com.example.Castlerole.config.JwtEntryPoint;
import com.example.Castlerole.config.JwtFilter;
import com.example.Castlerole.config.JwtTokenUtil;
import com.example.Castlerole.config.WebSecurityConfig;
import com.example.Castlerole.controller.JwtAuthenticationController;
import com.example.Castlerole.model.User;
import com.example.Castlerole.model.dto.UserDTO;
import com.example.Castlerole.repository.UserRepository;
import com.example.Castlerole.service.JwtUserService;
import com.example.Castlerole.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        JwtAuthenticationController.class
})

@WebAppConfiguration
@MockBeans(value = {

        @MockBean(UserService.class),
        @MockBean(AuthenticationManager.class),
        //@MockBean(UserDetailsService.class),
        @MockBean(JwtUserService.class),
        @MockBean(JwtTokenUtil.class),
        //@MockBean(UserDetails.class),
        //@MockBean(PasswordEncoder.class),
        //@MockBean(JwtEntryPoint.class),
        //@MockBean(UserRepository.class)
} )
public class AbstractTest {


    protected MockMvc mvc;

    @MockBean
    private JwtAuthenticationController jwtAuthenticationController;

    @Autowired
    private UserService userService;

    @Autowired
    @MockBean
    private JwtFilter jwtFilter;



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
