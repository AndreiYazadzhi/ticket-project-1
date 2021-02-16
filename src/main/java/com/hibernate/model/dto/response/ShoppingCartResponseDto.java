package com.hibernate.model.dto.response;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long cartId;
    private List<Long> ticketsIds;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<Long> getTicketsIds() {
        return ticketsIds;
    }

    public void setTicketsIds(List<Long> ticketsIds) {
        this.ticketsIds = ticketsIds;
    }
}
