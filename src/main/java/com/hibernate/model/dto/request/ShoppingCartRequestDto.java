package com.hibernate.model.dto.request;

import java.util.List;

public class ShoppingCartRequestDto {
    private Long userId;
    private List<Long> ticketsId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }
}
