package com.example.Castlerole.config;

import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;

//this class is used to generate token or to decode token into claims or a specific claim.
@Component
public class JwtTokenUtil implements Serializable {
    //https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
    private static final long serialVersionUID = -2550185165626007488L;
    //used to set expiration date, in this context in seconds, 5 * 60 * 60 => 5 hours.
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    //secret used to encode, classified in application.properties.
    @Value("${jwt.secret}")
    private String secret;




    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    //get claims based on lambda
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        //get al claims
        final Claims claims = getAllClaimsFromToken(token);
        //filter based on lambda
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        //decode token, use secret and return body.
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        //get expiration date.
        final Date expiration = getExpirationDateFromToken(token);
        //checks if date is before current date. if expiration is before today isTokenExpired => true, else false???? logical error possibly?
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //call helper token generator with claims empty and username.
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //4.   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        //calls Jwts builder => set claims =>
        return Jwts.builder().setClaims(claims)
                //set subject??
                .setSubject(subject)
                //set issued date in milliseconds
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //set expiration date in milliseconds, in this case JWT_TOKEN_VALIDITY * 1000 => in seconds.
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                //use claims and secret to encode, algorithm used is HS512.
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        //validate if the current user is the correct user.
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}