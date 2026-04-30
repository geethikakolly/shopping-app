package com.example.demo.Cart;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepo;
    private final CartItemRepository cartItemRepo;

    public CartService(CartRepository cartRepo, CartItemRepository cartItemRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
    }

    public Cart addToCart(Long userId, Long productId, int quantity){
        Cart cart= cartRepo.findByUserId(userId);

        if(cart==null){
            cart = new Cart();
            cart.setUserId(userId);
            cart = cartRepo.save(cart);
        }

        CartItem item = new CartItem();
        item.setCartId(cart.getId());
        item.setProductId(productId);
        item.setQuantity(quantity);

        cartItemRepo.save(item);
        return cart;
    }

    public List<CartItem> getCart(Long userId){
        Cart cart=cartRepo.findByUserId(userId);
        return cartItemRepo.findByCartId(cart.getId());
    }
}
