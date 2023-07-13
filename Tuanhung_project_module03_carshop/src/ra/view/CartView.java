package ra.view;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.CartItemController;
import ra.controller.UserController;
import ra.model.CartItem;
import ra.model.Bill;
import ra.model.User;

import java.util.List;

public class CartView {
    UserController userController = new UserController();
    CartItemController cartItemController = new CartItemController();
    List<User> listUser = new Config<User>().readFormFile(Config.PATH_USER);
    List<Bill> billList = new Config<Bill>().readFormFile(Config.PATH_PAY_HISTORY);
    List<CartItem> listItem = cartItemController.getListCartItem();
    public void cartView(){
        while (true){
            showListCart();
            System.out.println("*********************************************");
            System.out.println("*               \033[1;31m1. Xóa\033[0m                     *");
            System.out.println("*               \033[1;33m2. Thay đổi số lượng\033[0m       *");
            System.out.println("*               \033[1;32m3. Thanh toán\033[0m              *");
            System.out.println("*               \033[1;34m4. Trở lại trang chủ\033[0m       *");
            System.out.println("*********************************************");
            System.out.println("Nhập để chọn:");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    deleteCart();
                    break;
                case 2:
                    changeQuantity();
                    break;
                case 3:
                    payment();
                    break;
                case 4:
                    new UserView().userView();
                    break;
                default:
                    System.out.println("Chọn không chính xác!");
            }
        }
    }
    public void showListCart(){
        System.out.println("===================CART====================");
        int total = 0;
        for (CartItem cart: listItem) {
            System.out.println("Mã xe : "+ cart.getId() + " / " +"Tên xe : " + cart.getProduct().getProductName() + " / " +
                    "Số lượng : " + cart.getQuantity());
            total += cart.getProduct().getPrice()* cart.getQuantity();
        }
        System.out.println("Tổng tiền : " + total);
        System.out.println("-----------------------------------------------");
    }
    public void deleteCart(){
        System.out.println("Nhập id cần xoá");
        int id = InputMethods.getInteger();
        cartItemController.deleteCartItem(id);
    }
    public void changeQuantity(){
        System.out.println("Nhập mã đơn hàng:");
        int id = InputMethods.getInteger();
        System.out.println("Nhập số lượng:");
        int quantity = InputMethods.getInteger();

        List<CartItem> list = userController.getUserLogin().getList();
        boolean found = false;

        for (CartItem cartItem : list) {
            if (cartItem.getId() == id) {
                cartItem.setQuantity(quantity);
                found = true;
                break;
            }
        }

        if (found) {
            new Config<User>().writeFormFile(Config.PATH_USER, listUser);
            System.out.println("Số lượng đã được thay đổi.");
        } else {
            System.out.println("Không tìm thấy mã đơn hàng trong giỏ hàng.");
        }
    }
    public void payment(){
        String date = String.valueOf(java.time.LocalDateTime.now());
        Bill bill = new Bill(userController.getUserLogin().getId(),date, listItem);
        billList.add(bill);
        new Config<Bill>().writeFormFile(Config.PATH_PAY_HISTORY,billList);
        System.out.println("--------------------Hóa đơn---------------------");
        System.out.println(date);
        System.out.println("Khách hàng : " + userController.getUserLogin().getName());
        int total = 0;
        for (CartItem cart: listItem
        ) {
            System.out.println("Mã xe : "+ cart.getId() + " \n "+"Tên xe : "+ cart.getProduct().getProductName() + " \n "+"Số lượng : " + cart.getQuantity());
            total += cart.getProduct().getPrice()* cart.getQuantity();
        }
        System.out.println("Tổng tiền : " + total);
        System.out.println("-------------------------------------------------");
        cartItemController.payment();
    }
}
