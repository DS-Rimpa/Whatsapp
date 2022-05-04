package com.notify.notify.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Users {

    private String message_type;
    private String to;
    private String from;
    private String channel;
    private String text;



}
