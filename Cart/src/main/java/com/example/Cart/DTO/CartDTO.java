package com.example.Cart.DTO;

import com.example.Cart.Entity.CartItem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;

    @OneToMany(mappedBy = "cart",orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CartItem> cartItems = new ArrayList<>();

    public CartDTO(int cartId, List<CartItem> cartItems,long totalPrice) {
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    private long totalPrice;

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }


}
