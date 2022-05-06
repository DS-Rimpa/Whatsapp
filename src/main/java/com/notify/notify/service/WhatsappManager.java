//package com.notify.notify.service;
//
//import com.amazonaws.services.lambda.runtime.events.SQSEvent;
//import com.google.gson.Gson;
//import com.notify.notify.model.Entity;
//import com.notify.notify.model.Response;
//import com.notify.notify.model.Users;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//
//public class WhatsappManager {
//    private static final Logger logger = LoggerFactory.getLogger(WhatsappManager.class);
//    private final Gson gson;
//
//
//    public WhatsappManager(Gson gson) {
//        this.gson = gson;
//    }
//
//    public Users messageBodyToRequest(String body) {
//        return gson.fromJson(body, Users.class);
//    }
//
//    private void sendMessage(Users request) {
//        logger.info("whatsapp request:{}", request);
//        Users message = Users.builder()
//                .channel(request.getChannel())
//                .message_type(request.getMessage_type())
//                .from(request.getFrom())
//                .text(request.getText())
//                .to(request.getTo())
//                .build();
//        System.out.println("Whatsapp Message : " + message.toString());
//        Response response;
//        try {
//            WhatsAppServiceImpl whatsAppService = new WhatsAppServiceImpl(gson);
//            response = whatsAppService.addUser(message);
//
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public void processEvents(SQSEvent event) {
//        System.out.println("Process Event :{}" + event);
//        logger.info("Process Event :{}" ,event);
//        event.getRecords().stream()
//                .map(SQSEvent.SQSMessage::getBody)
//                .map(this::messageBodyToRequest)
//                .forEach(this::sendMessage);
//        logger.info("request from event :{}", event);
//    }
//}