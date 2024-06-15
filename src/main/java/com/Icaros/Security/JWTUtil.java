package com.Icaros.Security;

import java.util.Date;
import java.util.Objects;
import java.util.HashMap;
import java.util.Map;

import java.security.SecureRandom;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	@Value("${jwt.secret}")
	private String  secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String genereateToken(String email) {
		SecretKey key = getKeyBySecret();
		return Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + this.expiration))
				.signWith(key).compact();
	}

	private SecretKey getKeyBySecret() {
		SecretKey key = Keys.hmacShaKeyFor(this.secret.getBytes());
		return key;
	}

	public boolean isValidToken(String token) {
		Claims claims = getClaims(token);
		if (Objects.nonNull(claims)) {
			String email = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (Objects.nonNull(email) && Objects.nonNull(expirationDate) && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	
	
	public String getEmail(String token) {
		Claims claims = getClaims(token);
		if(Objects.nonNull(claims)){
			return claims.getSubject();
		}
		return null;
	}
	
	private final Map<String, String> tokenStorage = new HashMap<>();
    private final SecureRandom random = new SecureRandom();
	
	public String generateTokenEmail(String email) {
        String token = String.format("%04d", random.nextInt(10000));
        tokenStorage.put(email, token);
        return token;
    }

    public boolean verifyToken(String email, String token) {
        return token.equals(tokenStorage.get(email));
    }
	
	private Claims getClaims(String token) {
		SecretKey key= getKeyBySecret();
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}

	  public Claims decodeJwt(String token) {
	        SecretKey key = getKeyBySecret();
	        try {
	            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
	            return claimsJws.getBody();
	        } catch (Exception e) {
	            // Trate a exceção, se necessário
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	
	
	

}
