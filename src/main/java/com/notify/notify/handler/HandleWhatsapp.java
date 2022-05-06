package com.notify.notify.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.google.gson.Gson;
import com.notify.notify.service.WhatsAppServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HandleWhatsapp implements RequestHandler<SQSEvent,Void> {

    private static final Logger logger = LoggerFactory.getLogger(HandleWhatsapp.class);
    private final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    private final Gson gson = new Gson();

    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
//        new WhatsappManager(gson).processEvents(sqsEvent);
        new WhatsAppServiceImpl(gson).processEvents(sqsEvent);
        return null;
//        WhatsappManager whatsappManager=new WhatsappManager(gson);
//        sqsEvent.getRecords().forEach(m -> whatsappManager.processEvents(m.getBody()));
//        return null;
    }
}
