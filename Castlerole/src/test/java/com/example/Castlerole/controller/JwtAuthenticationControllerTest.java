package com.example.Castlerole.controller;

import com.example.Castlerole.AbstractClass.AbstractTest;
import com.example.Castlerole.model.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class JwtAuthenticationControllerTest extends AbstractTest {


    @Override
    @Before
    public void setUp() {
        super.setUp();

    }







    public static UserDTO createDTO( String username, String password) {
        UserDTO dto = new UserDTO();

        dto.setUsername(username);
        dto.setPassword(password);

        return dto;
    }


    @Test
    public void login() {
    }



    //@WithMockUser(value = "User",username = "Admin12312", password = "passwor")
    @Test
    public void register() throws Exception {
        String uri = "/register";
        final String token = "akfkldakkadjfiafkakflkd";

        UserDTO user = createDTO("admin1234133","password");


        String inputJson = super.mapToJson(user);




        //Mockito.when(userRepository.save(user)).thenReturn(Mono.just(employee));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)
                .characterEncoding("utf-8")
                .header("Cache-Control","no-cache, no-store")
        )
                .andDo(print())
                .andDo(MockMvcResultHandlers.log())
                .andExpect(status().isOk())
                .andReturn();
        var status2 = mvcResult.getResponse();
        System.out.println(status2.getContentType());

        int status = mvcResult.getResponse().getStatus();

        System.out.println(mvcResult.getModelAndView());
        System.out.println(inputJson);
        //assertEquals(201,status);

        String content = mvcResult.getResponse().getContentType();
        //assertEquals(content, "user created");

//        HashMap<String, String> map = new HashMap<>();
//        map.put("greeting","Hello, world!");

    }
}