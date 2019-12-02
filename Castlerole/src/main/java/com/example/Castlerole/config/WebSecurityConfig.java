package com.example.Castlerole.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//when prepost set to true, method lvl annotation is set.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        //csrf crossite, csrf().disable => disable crosssite origin? -> cant call from angular since different port origins.
        httpSecurity.csrf().disable()
                //autherize antmatchers any request with /api/ will be authenticated. and else exceptionhandling
                .authorizeRequests().antMatchers("**/game/").authenticated()
                .and()
                //if not autherized -> call jwtEntryPoint.
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                //make session stateless -> sessionmanage policy => stateless, why find out later....
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //Add a filter to validate the tokens with every request
        //In spring boot UsernamePasswordAuthenticationFilter is a default class.
        //filter validates based on jwtFilter class and UsernamePasswordAuthenticationFilter class.
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        //adds default headers for the request.
        httpSecurity.headers().cacheControl();
    }
}