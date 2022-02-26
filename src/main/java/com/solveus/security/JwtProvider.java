package com.solveus.security;

import com.solveus.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${spring.jwt.secret.access}")
    private String ACCESS_KEY;

    @Value("${spring.jwt.secret.refresh}")
    private String REFRESH_KEY;


//    private  final long ACCESS_TOKEN_VALID_TIME = 3 * 60 * 1000L; // 3분
//    private  final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L; // 1주

    private  final long ACCESS_TOKEN_VALID_TIME = 3 * 60 * 10000L; // 3분
    private  final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 24 * 7 * 1000L; // 1주

//    @Value("${spring.jwt.accessExpire}")
//    private long  ACCESS_TOKEN_VALID_TIME;
//    @Value("${spring.jwt.refreshExpire}")
//    private long  REFRESH_TOKEN_VALID_TIME;


    private final UserDetailsService userDetailsService;

    // 객체 초기호, key를 Base64로 인코딩
    @PostConstruct
    protected void init() {
//        System.out.println("ACCESS_KEY: " + ACCESS_KEY);
//        System.out.println("REFRESH_KEY: " + REFRESH_KEY);
        ACCESS_KEY = Base64.getEncoder().encodeToString(ACCESS_KEY.getBytes());
        REFRESH_KEY = Base64.getEncoder().encodeToString(REFRESH_KEY.getBytes());
    }

    //access 토큰 생성
    public String createAccessToken(String userID)
    {
        //user 구분 위해 Claims(token payload)에 userPK 값을 넣어준다.
        Claims claims = Jwts.claims();
        claims.put("userID", userID);
        claims.put("roles", "USER"); // 모두 USER 권한 갖는다.
        //claims.put("ROLE", "USER");// 로그인 모두 사용자 권한 가진다.
        //생성 날짜, 만료 날자 위한 Date : 현재 시점 + 설정
        Date now = new Date();

        System.out.println(now.getTime());
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 정보
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) //accessToken 만료시간
                .signWith(SignatureAlgorithm.HS256, ACCESS_KEY)
                .compact();
    }

    //refresh 토큰 생성 - 유저가 DB에 등록된 index
    public String createRefreshToken(Long userID)
    {
        //user 구분 위해 Claims(token payload)에 userPK 값을 넣어준다.
        Claims claims = Jwts.claims();
        claims.put("userID", userID);
        //claims.put("ROLE", "USER");// 로그인 모두 사용자 권한 가진다.
        //생성 날짜, 만료 날자 위한 Date : 현재 시점 + 설정
        Date now = new Date();
        Date expiration = new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME);

        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 정보
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, REFRESH_KEY)
                .compact();
    }

    //HTTP request header에서 Token parsing -> "X-AUTHH-TOKEN":"jwt토큰"
    public String resolveAccessToken(HttpServletRequest request){
        return request.getHeader("ACCESS_TOKEN");
    }

    public String resolveRefreshToken(HttpServletRequest request){
        return request.getHeader("REFRESH_TOKEN");
    }

    public Claims getClaimsFromAccessToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(ACCESS_KEY))
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getClaimsFromRefreshToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(REFRESH_KEY))
                .parseClaimsJws(token)
                .getBody();
    }

    //Access jwt 유효성 & 만료일 확인
    public boolean isValidAccessToken(String token){
        try {
           Claims accessClaims = getClaimsFromAccessToken(token);
            System.out.println("Access exipreTime: " + accessClaims.getExpiration());
            System.out.println("Access userID: " + accessClaims.get("userID"));
            return true;
        }catch (ExpiredJwtException e){ // 만료된 경우 해당 ID의 accessToken 만료되었음을 알린다.
            System.out.println("Token Expired UserID: " + e.getClaims().get("userID"));
            return false;
        }catch (JwtException e){
            System.out.println("Token Tampered");
            return false;
        }catch(NullPointerException e) {
            System.out.println("Token is null");
            return false;
        }
    }

    //refresh jwt 유효성 & 만료일 확인
    public boolean isValidRefreshToken(String token){
        try {
            Claims refreshClaims = getClaimsFromRefreshToken(token);
            System.out.println("Refresh exipreTime: " + refreshClaims.getExpiration());
            System.out.println("Refresh userID: " + refreshClaims.get("userID"));
            return true;
        }catch (ExpiredJwtException e){ // 만료된 경우 해당 ID의 refresh token이  만료되었음을 알린다.
            System.out.println("Token Expired UserID: " + e.getClaims().get("userID"));
            return false;
        }catch (JwtException e){
            System.out.println("Token Tampered");
            return false;
        }catch(NullPointerException e) {
            System.out.println("Token is null");
            return false;
        }
    }

    // AccessToken에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) getClaimsFromAccessToken(token).get("userID"));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }

}
