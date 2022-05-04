//package com.notify.notify.handler;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.amazonaws.services.lambda.runtime.events.SQSEvent;
//import com.notify.notify.model.Response;
//import com.notify.notify.model.Users;
//import com.notify.notify.service.WhatsAppServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public class SQSRequestHandler implements RequestHandler<Users, Response> {
//    private static final Logger logger = LoggerFactory.getLogger(SQSRequestHandler.class);
//
//
//    @Override
//    public Response handleRequest(Users users, Context context) {
//        WhatsAppServiceImpl appService = new WhatsAppServiceImpl();
//
//        return appService.addUser(users);
//    }
//}
