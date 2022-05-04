//package com.notify.notify.handler;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.amazonaws.services.lambda.runtime.events.SQSEvent;
//import com.notify.notify.model.Response;
//import com.notify.notify.model.Users;
//import com.notify.notify.service.WhatsAppServiceImpl;
//import com.notify.notify.utils.EventUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Whatsapp implements RequestHandler<SQSEvent, Void> {
//
//    private static final Logger logger = LoggerFactory.getLogger(Whatsapp.class);
//
//    @Override
//    public Void handleRequest(SQSEvent sqsEvent, Context context) {
//        logger.info("Message received");
//        WhatsAppServiceImpl action = new WhatsAppServiceImpl(context);
//        for (SQSEvent.SQSMessage msg : sqsEvent.getRecords()){
//            Users users = null;
//
//            try {
//                logger.info("Executing action for sqs message");
//                logger.info("Message: {}", msg.getBody());
//                users = EventUtils.getMessageFromSQSMessage(msg);
//                action.execute(users);
//
//            } catch (Exception e) {
//                logger.info("error");
//            }
//        }
//        return null;
//    }
//}
