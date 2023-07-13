package ra.model;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

public class Bill implements Serializable {
    private int userId;
    private String date;
    private List<CartItem> list = new ArrayList<>();

    public Bill() {
    }

    public Bill(int userId,String date,List<CartItem> list) {
        this.userId = userId;
        this.list = list;
        this.date = date;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void displayBill(){
        System.out.println("-------------------------------------------------------");
        System.out.println("Thời gian : " + date);
        System.out.println("Mã người dùng : " + userId);
        int total = 0;
        for (CartItem cart: list
        ) {
            System.out.println("Mã xe : "+ cart.getId() + " / " +"Tên xe : " + cart.getProduct().getProductName() + " / " +
                    "Số lượng : " + cart.getQuantity());
            total += cart.getProduct().getPrice()* cart.getQuantity();
        }
        System.out.println("Tổng tiền : " + total);
        System.out.println("-------------------------------------------------------");
    }
}

