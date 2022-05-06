package com.notify.notify.service;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.notify.notify.config.NexmoConfig;
import com.notify.notify.model.Response;
import com.notify.notify.model.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


public class WhatsAppServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(WhatsAppServiceImpl.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private NexmoConfig nexmoConfig;
    private Users users;
    private final Gson gson;

    Response response = new Response();

    public WhatsAppServiceImpl(Gson gson) {
        this.gson = gson;
    }

    public Response addUser(Users user) {
        try {
            String vonageUrl = "https://messages-sandbox.nexmo.com/v1/messages";


            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            Response nexmoResponse = null;
            logger.info("Request in string :: {}", user);
//            String auth = nexmoConfig.getApiKey() + ":" + nexmoConfig.getApiSecret();
            HttpEntity<Users> httpEntity = new HttpEntity<Users>(user, httpHeaders);
            logger.info("Request in Http Entity :: {}", httpEntity);
//            httpHeaders.setBasicAuth(nexmoConfig.getApiKey(), nexmoConfig.getApiSecret());
            httpHeaders.setBasicAuth("6eba22d5","QmXBt61Pq3klggFI");
//            httpHeaders.setBasicAuth(nexmoConfig.getApiKey(), nexmoConfig.getApiSecret());
            URI uri = new URI(vonageUrl);
//            URI uri = new URI(nexmoConfig.getUri());
            logger.info("Url returned is :: {}", uri);
            String nexmoURI = UriComponentsBuilder.fromUri(uri).build().toUriString();
            logger.info("value of nexmoUri returned is :: {}", nexmoURI);
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(nexmoURI, httpEntity, String.class);
            logger.info("Request in response Entity :: {}", responseEntity.getBody());
            if (responseEntity.getBody().contains("message_uuid")) {
                nexmoResponse = new ObjectMapper().readValue(responseEntity.getBody(), Response.class);
                response.setMessage_uuid(nexmoResponse.getMessage_uuid());

            }
            ResponseEntity<Users> res = restTemplate.postForEntity(vonageUrl, httpEntity, Users.class);
//            ResponseEntity<Users> res = restTemplate.postForEntity(nexmoConfig.getUri(), httpEntity, Users.class);
            ObjectMapper objectMapper = new ObjectMapper();
            return response;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return response;
    }
    public void processEvents(SQSEvent event) {
        logger.info("Process Event :: {}" , event);
        event.getRecords().stream()
                .map(SQSEvent.SQSMessage::getBody)
                .map(this::messageBodyToRequest)
                .forEach(this::addUser);
        logger.info("request from event :{}", event);
    }
    public Users messageBodyToRequest(String body) {
        return gson.fromJson(body, Users.class);
    }
}
