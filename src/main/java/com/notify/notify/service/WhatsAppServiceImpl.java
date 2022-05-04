package com.notify.notify.service;

import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.notify.notify.config.NexmoConfig;
import com.notify.notify.model.Response;
import com.notify.notify.model.Users;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


public class WhatsAppServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(WhatsAppServiceImpl.class);

    private RestTemplate restTemplate;
    private NexmoConfig nexmoConfig;
    private Users users;
    private final Gson gson;

    Response response=new Response();

    public WhatsAppServiceImpl( Gson gson) {
        this.gson=gson;
    }

//    public WhatsAppServiceImpl(Context context) {
//        super(context);
//
//    }
//
//    @Override
//    protected Object invoke(Users event) {
//        Response response= addUser(event);
//        return response;
//
//    }


    public Response addUser(Users user) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            Response nexmoResponse=null;
            logger.info("Request in string :: {}", user);
            String auth = nexmoConfig.getApiKey()+":"+nexmoConfig.getApiSecret();
            HttpEntity<Users> httpEntity = new HttpEntity<Users>(user, httpHeaders);
            logger.info("Request in Http Entity :: {}", httpEntity);
            httpHeaders.setBasicAuth(nexmoConfig.getApiKey(),nexmoConfig.getApiSecret());
            URI uri=new URI(nexmoConfig.getUri());
            String nexmoURI= UriComponentsBuilder.fromUri(uri).build().toUriString();
            ResponseEntity<String> responseEntity=restTemplate
                    .exchange(nexmoURI, HttpMethod.POST,httpEntity,String.class);
            logger.info("Request in Http Entity :: {}", responseEntity.getBody());
            if(responseEntity.getBody().contains("message_uuid")){
                nexmoResponse=new ObjectMapper().readValue(responseEntity.getBody(),Response.class);
                response.setMessage_uuid(nexmoResponse.getMessage_uuid());

            }
            ResponseEntity<Users> res = restTemplate.postForEntity(nexmoConfig.getUri(), httpEntity, Users.class);
            ObjectMapper objectMapper= new ObjectMapper();
            return response;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
        return response;

    }

    public void processEvents(SQSEvent event){
        System.out.println("Process Event :{}"+event);
        logger.info("Process Event :{}"+event);
        event.getRecords().stream()
                .map(SQSEvent.SQSMessage::getBody)
                .map(this::messageBodyToRequest)
                .forEach(this::addUser);
    }
    public Users messageBodyToRequest(String body){
        return gson.fromJson(body,Users.class);
    }


}



