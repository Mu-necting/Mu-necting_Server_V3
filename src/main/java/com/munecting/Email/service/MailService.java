package com.munecting.Email.service;

import org.springframework.stereotype.Service;

@Service
public interface MailService {

    //메일 발송
    String sendSimpleMessage(String to) throws Exception;

    //검증
    String verifyCode(String code);
}
