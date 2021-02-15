package com.hibernate.model.dto.response;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long cartId;
    private List<Long> ticketsId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }
}
