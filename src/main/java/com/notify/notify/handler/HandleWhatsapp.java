package com.notify.notify.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.google.gson.Gson;
import com.notify.notify.service.WhatsAppServiceImpl;

public class HandleWhatsapp implements RequestHandler<SQSEvent,Void> {


    private final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    private final Gson gson = new Gson();
    @Override
    public Void handleRequest(SQSEvent sqsEvent, Context context) {
        new WhatsAppServiceImpl(gson).processEvents(sqsEvent);
        return null;
    }
}
