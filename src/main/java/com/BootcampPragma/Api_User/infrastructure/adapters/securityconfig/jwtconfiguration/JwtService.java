package com.BootcampPragma.Api_User.infrastructure.adapters.securityconfig.jwtconfiguration;

import com.BootcampPragma.Api_User.infrastructure.adapters.persistance.entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "mi_mama-Me_mima-_miMama_meAma";

    public String getToken(UserEntity user){
        return generateToken(new HashMap<>(),user);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            @NotNull UserDetails userDetails

    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
<<<<<<< Updated upstream
                .setSubject(userDetails.getUsername())
=======
                .setSubject(String.valueOf(user.getId()))
>>>>>>> Stashed changes
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generate(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "USER");
        return generateToken(claims, userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userId = extractUserId(token);
        return (userId.equals(String.valueOf(userDetails.getUsername())));
    }


    private Claims extractAllClaims(String token) throws SignatureException {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            e.printStackTrace();
            return Jwts.claims();
        }
    }
}
