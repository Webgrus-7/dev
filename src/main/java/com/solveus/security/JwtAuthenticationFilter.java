package com.solveus.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// 검증 끝난 JWT로 부터 유저 정보 받아와 UsernamePasswordAuthenticationFilter로 전달
// 인증 작업 진행
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtProvider jwtProvider;

    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext에 저장하는 역할을 수행한다.
    //request로 들어오는 Jwt 유효성 검증
    @Override
    public void doFilter(ServletRequest request, ServletResponse response ,
                         FilterChain filterChain) throws IOException, ServletException {

        //1. 헤더에서 ACCESS 토큰 가져온다.
        String token = jwtProvider.resolveAccessToken((HttpServletRequest) request);
        // 2. //토큰이 유효할 경우n -> 토큰으로 Authentication 가져와서 SecurityContext에 저장한다.

        if(token != null && jwtProvider.isValidAccessToken(token)){
            Authentication authentication = jwtProvider.getAuthentication(token); // 유저 정보 받아온다.
            SecurityContextHolder.getContext().setAuthentication(authentication); // SecurityContext 에 Authentication 객체 저장
        }
        filterChain.doFilter(request,response);
    }
}
