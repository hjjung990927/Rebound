package com.example.rebound.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmsService {
    //    코드 생성
    private String createCode(){
        String codes = "0123456789";
        String code = "";
        Random random = new Random();

        for(int i=0; i<6; i++){
            code += codes.charAt(random.nextInt(codes.length()));
        }

        return code;
    }

    //    문자 발송
    public String send(String phoneNumber) {
        String code = createCode();

//        각자 api key 입력
//        String api_key = "NCSUUCWS4CL3Q8AZ";
//        String api_secret = "IC206OKQZUXLNSVSPAAGBSOZARN7KDEV";
        String api_key = "";
        String api_secret = "";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);
//        전송하는 번호 입력
        params.put("from", "01055767406");
        params.put("type", "SMS");
        params.put("text", "Rebound 인증번호: " + code);
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        return code;
    }
}
