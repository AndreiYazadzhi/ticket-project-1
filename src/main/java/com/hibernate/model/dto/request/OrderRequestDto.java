package com.hibernate.model.dto.request;

import java.util.List;

public class OrderRequestDto {
    private String userEmail;
    private List<Long> ticketsId;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }
}
