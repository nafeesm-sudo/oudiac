package com.app.oudiac.services.JWTService;

import com.app.oudiac.models.User;
import com.app.oudiac.utils.ConstantUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ott.InvalidOneTimeTokenException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private ObjectMapper objectMapper;

    public String generateJwtToken(User user){
        Map<String,Object> payload = new HashMap<>();
        Long nowInMillis = System.currentTimeMillis();
        payload.put("iat",new Date(nowInMillis));
        payload.put("exp",new Date(nowInMillis + ConstantUtils.JWT_TOKEN_EXPIRATION_TIME));
        payload.put("userId",user.getId());
        payload.put("iss","scaler");
        payload.put("scope",user.getRole());

        return Jwts.builder().claims(payload).signWith(secretKey).compact();
    }
    public Boolean validateToken(String token,Long userId) {

        try{
            JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
            Claims claims = jwtParser.parseSignedClaims(token).getPayload();

            Date tokenExpiry =(Date) claims.get("exp");

            Date expiration = claims.getExpiration();

            if (expiration.before(new Date())) {
                throw new InvalidOneTimeTokenException("Token has expired!!");
            }

            System.out.println(tokenExpiry);
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
