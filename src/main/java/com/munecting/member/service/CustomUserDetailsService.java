//package com.munecting.member.service;
//
//import com.munecting.member.dto.MemberDto;
//import com.munecting.member.entity.Member;
//import com.munecting.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@RequiredArgsConstructor
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//
//    //username -> mail
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByUsername(username);
//        log.info(member.toString());
//        log.info("loadUserByUsername");
//        if (member != null) {
//            log.info(member.toString());
//            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
//            return new CustomUserDetails(member);
//        }
//        return null;
//    }
//
////    public UserDetails
//}
