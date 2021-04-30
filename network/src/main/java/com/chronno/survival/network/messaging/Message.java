package com.chronno.survival.network.messaging;

import java.time.LocalDateTime;

public class Message {

    private String type;
    private String token;
    private String payload;
    private String dateTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(this.dateTime);

    }

    public void setDateTime(LocalDateTime dateTime) {
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", token='" + token + '\'' +
                ", payload='" + payload + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }
}
