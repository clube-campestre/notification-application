package com.campestre.clube.notification_application.dto;
import java.io.Serializable;

public class EmailMessageDto implements Serializable {

    private String email;
    private String code;

    public EmailMessageDto() {} // necessário para Jackson

    public EmailMessageDto(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
}