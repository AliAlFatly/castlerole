package com.example.castlerole.config;


import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//this class is used to handle unauthorized requests. it is used in webSecurityConfig.
//NOTE must implement interface AuthenticationEntryPoint to be recognizable of a matching class in webSecurityConfig httpSecurity field. else error type mismatch.
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint, Serializable {
    //serialVersionUID unknown purpose????
    private static final long serialVersionUID = -7858869558953243875L;
    //to implement function according to interface blue print.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //this method is called when ever a request is made to a url with .authenticated() when the user is not authorized.
        //return response.sendError ( unauthorized => status code 401/403 with the message "Unauthorized").
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}