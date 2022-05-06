package com.notify.notify.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
public class Entity {
    private String message_type;
    private String to;
    private String from;
    private String channel;
    private String text;

}
