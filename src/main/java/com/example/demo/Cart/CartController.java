package com.example.demo.Cart;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long userId,@RequestParam Long productId,
                            @RequestParam int quantity){
        service.addToCart(userId,productId,quantity );
        return "Item added to the cart";
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable Long userId){
        return service.getCart(userId);
    }

}
