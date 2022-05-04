package com.notify.notify.utils;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notify.notify.model.Users;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class EventUtils {

    public static Users getMessageFromSQSMessage(SQSEvent.SQSMessage message) throws Exception {
        return getMessageFromPayload(message.getBody());
    }

    private static Users getMessageFromPayload(String payload) throws Exception {
        if (StringUtils.isBlank(payload))
        {
            System.out.println("Message Body is blank");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(payload,Users.class);
        }
        catch (IOException ex){
            throw new Exception("Error parsing message body"+ex.getMessage());
        }
    }
}
