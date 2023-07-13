package ra.controller;

import ra.model.CartItem;
import ra.service.CartItemServiceIMPL;
import ra.service.ICartItemService;

import java.util.List;

public class CartItemController {
    ICartItemService cartItemService = new CartItemServiceIMPL();
    public List<CartItem> getListCartItem(){
        return cartItemService.fileAll();
    }
    public void createCartItem(CartItem cartItem){
        cartItemService.save(cartItem);
    }
    public void findById(int id){
        cartItemService.findById(id);
    }
    public void deleteCartItem(int id){
        cartItemService.deleteById(id);
    }
    public void payment(){
        cartItemService.payment();
    }
}

