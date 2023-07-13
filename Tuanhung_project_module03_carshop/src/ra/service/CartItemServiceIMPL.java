package ra.service;

import ra.config.Config;
import ra.model.CartItem;
import ra.model.User;
import ra.service.UserServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class CartItemServiceIMPL implements ICartItemService {
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();
    List<User> userList = userServiceIMPL.fileAll();
    User userLogin = userServiceIMPL.getCurrentUser();
    List<CartItem> cartItemList;

    public CartItemServiceIMPL() {
        for (User user : userList) {
            if (user.getId() == userLogin.getId()) {
                cartItemList = user.getList();
                break;
            }
        }
        if (cartItemList == null) {
            cartItemList = new ArrayList<>();
        }
    }

    @Override
    public List<CartItem> fileAll() {
        return cartItemList;
    }

    @Override
    public void save(CartItem cartItem) {

        if (cartItemList.size() == 0) {
            cartItemList.add(cartItem);
        } else {
            boolean check = false;
            for (CartItem cartItem1 : cartItemList) {
                if (cartItem1.getProduct().getProductId() == cartItem.getProduct().getProductId()) {
                    check=true;
                    cartItem1.setQuantity(cartItem1.getQuantity() + cartItem.getQuantity());
                    break;
                }
            }
            if (!check){
                cartItemList.add(cartItem);
            }
        }
        userLogin.setList(cartItemList);
        userServiceIMPL.save(userLogin);
    }

    @Override
    public CartItem findById(int id) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getId() == id) {
                return cartItem;
            }
        }
        return null;
    }
    @Override
    public void deleteById(int id) {
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getId() == id) {
                cartItemList.remove(cartItem);
                new Config<User>().writeFormFile(Config.PATH_USER,userList);
                break;
            }
        }
    }

    @Override
    public void payment() {
        userLogin.setList(new ArrayList<>());
        userServiceIMPL.save(userLogin);
    }
}
