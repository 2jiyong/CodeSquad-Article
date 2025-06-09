package com.example.demo.common.auth;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;


@Component
public class JwtTokenProvider {
  private static final String base64Secret = "thisIsASecretKeyThatIsAtLeast32BytesLongaaa";

  private final long validityInMs = 6 * 3600000;
  private Key key;

  @PostConstruct
  public void init() {
    this.key = Keys.hmacShaKeyFor(java.util.Base64.getUrlDecoder().decode(base64Secret));
  }

  public String createToken(Long userId, String username) {
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("userId", userId);

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMs);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, key)
        .compact();
  }

  public Claims parseClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(key).build()
        .parseClaimsJws(token).getBody();
  }
}
