//package com.notify.notify.handler;
//
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestHandler;
//import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
//import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
//import com.google.gson.Gson;
//import com.notify.notify.model.Response;
//import com.notify.notify.model.Users;
//import com.notify.notify.service.WhatsAppServiceImpl;
//
//public class WhatsAppHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
//    private final Gson gson = new Gson();
//
//    @Override
//    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
//        WhatsAppServiceImpl whatsAppService=new WhatsAppServiceImpl();
//        Response response;
//        APIGatewayProxyResponseEvent responseEvent=new APIGatewayProxyResponseEvent();
//        try {
//            response=whatsAppService.addUser(gson.fromJson(request.getBody(),Users.class));
//
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//            return responseEvent;
//        }
//        responseEvent.setBody(gson.toJson(response));
//        return responseEvent;
//    }
//
//
//
//}
