package com.munecting.config.jwt;

//import com.munecting.member.dto.MemberDto;
//import com.munecting.member.entity.Member;
//import com.munecting.member.service.CustomUserDetails;
//import com.munecting.member.service.CustomUserDetailsService;
import com.munecting.oauth2.service.CustomOAuth2User;
import com.munecting.oauth2.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    // 한번만 request에 동작하는 필터를 extends
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");
        log.info(authorization);
        if (authorization == null || !authorization.startsWith("Bearer ")) {

            log.info("token is not enabled");
            filterChain.doFilter(request, response); // 다음 필터로 req,res를 보낸다

            return ;
        } // 토큰이 이상하면 로그인 화면으로

        log.info("authorization now");

        // Bearer 제거 후 순수 토큰 획득

        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) {

            log.info("token expired");
            filterChain.doFilter(request, response);

            return;
        }  // 토큰이 만료되면 로그인 화면으로

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        log.info(username);
        log.info(role);

        //userDTO를 생성하여 값 set
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);
        //UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        log.info("세션등록");
        filterChain.doFilter(request, response);
    }
}
