package no.lagalt.lagaltbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;
    public static final long JWT_TOKEN_VALIDITY = 90 * 24 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public String getUsernameFromToken(String token) throws UnsupportedEncodingException {
        String identity = (String) getAllClaimsFromToken(token).get("identity");
        log.trace("Fetching identity: {}",  identity);
        if (identity==null) {
            identity = (String) getAllClaimsFromToken(token).getSubject();
        }
        log.trace("Fetching subject: {}",  identity);
        return identity;
    }

    public Date getExpirationDateFromToken(String token) throws UnsupportedEncodingException {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws UnsupportedEncodingException {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) throws UnsupportedEncodingException {
        try {
            return Jwts.parser().setSigningKey(secret.getBytes("US-ASCII")).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            log.trace("Token expired... claims: {}", e.getClaims().toString());
            return e.getClaims();
        }
    }

    private Boolean isTokenExpired(String token) throws UnsupportedEncodingException {


        log.trace("Returning false for isTokenExpired()");
        return false;
    }

    public String generateToken(UserDetails userDetails) throws UnsupportedEncodingException {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) throws UnsupportedEncodingException {
        Date now = new Date(System.currentTimeMillis());
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .setNotBefore(now)
                .setHeaderParam("typ","JWT")
                .signWith(SignatureAlgorithm.HS256, secret.getBytes("US-ASCII")).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws UnsupportedEncodingException {
        log.debug("In JwtTokenUtil.validateToken()......");
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
