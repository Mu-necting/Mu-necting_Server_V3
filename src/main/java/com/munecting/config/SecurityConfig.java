package com.munecting.config;

//import com.munecting.config.jwt.JWTFilter;
//import com.munecting.config.jwt.JWTFilter;
//import com.munecting.config.jwt.JWTUtil;
import com.munecting.config.jwt.JWTFilter;
import com.munecting.config.jwt.JWTUtil;
import com.munecting.oauth2.service.CustomOAuth2UserService;
import com.munecting.oauth2.service.CustomSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@Configuration
@EnableWebSecurity(debug = true)
//@EnableMethodSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil) {

        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }
    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
//    private final AuthenticationConfiguration authenticationConfiguration;
//    private final JWTUtil jwtUtil;
//
//    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration,JWTUtil jwtUtil) {
//
//        this.authenticationConfiguration = authenticationConfiguration;
//        this.jwtUtil = jwtUtil;
//    }


    //AuthenticationManager Bean 등록
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        log.info("authenticationManager");
//        return configuration.getAuthenticationManager();
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.info("filterChain");
//        http.csrf((csrf) -> csrf.disable());
//
//
//        //http basic 인증 방식 disable
//        http
//                .httpBasic((auth) -> auth.disable());
//
//        http.authorizeHttpRequests((auth) -> auth
//                .requestMatchers("/").permitAll()
//                .anyRequest().authenticated());
//        //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
//        http
//                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
//
//        http
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil), UsernamePasswordAuthenticationFilter.class);
//
//
//        http
//                .sessionManagement((session) -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));//JWT를 통한 인증/인가를 위해서 세션을 STATELESS 상태로 설정하는 것이 중요하다.
        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());
        //JWTFilter 추가
        http
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        //oauth2
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                );

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login/**", "/error/**","/api/mail/**").permitAll()
                        .anyRequest().authenticated());
        //세션 설정 : STATELESS
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
