package com.notify.notify.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.notify.notify.model.Response;
//import com.notify.notify.model.Users;
//import com.notify.notify.service.WhatsAppServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@ComponentScan(basePackages = {"com.notify.notify.handler"})
//public class Test {
//    private WhatsAppServiceImpl service;
//    private Users users;
//    private static ObjectMapper objectMapper = new ObjectMapper();
//    public void process(final String msg){
//        try{
//            log.info("received message: {}",msg);
//            final Users users = objectMapper.readValue(msg,Users.class);
//            final Response response=service.addUser(users);
//            log.info("Response entity : {}",response.getMessage_uuid());
//
//        }catch (Exception e)
//        {
//            log.error("Exception occurs while processing", e);
//        }
//    }
//}
