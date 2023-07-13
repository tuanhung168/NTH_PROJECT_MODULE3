package ra.view;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.CartItemController;
import ra.controller.ProductControl;
import ra.controller.UserController;
import ra.model.CartItem;
import ra.model.Product;

import java.util.List;

public class UserView {
    UserController userController = new UserController();
    ProductControl productControl = new ProductControl();
    CartItemController cartItemController = new CartItemController();
    List<Product> productList = productControl.getProducts();
    List<CartItem> cartItemList = cartItemController.getListCartItem();

    public void userView() {
        while (true) {
            System.out.println("*********************** \033[1;35mWelcome " + userController.getUserLogin().getUserName() + "\033[0m *************************");
            showListProduct();
            System.out.println("****************************************************************************************************************");
            System.out.println("*                                           \033[1;32m1. Xem chi tiết xe\033[0m                                                *");
            System.out.println("*                                           \033[1;33m2. Thêm vào giỏ hàng\033[0m                                              *");
            System.out.println("*                                           \033[1;36m3. Đến giỏ hàng\033[0m                                                   *");
            System.out.println("*                                           \033[1;34m4. Tìm kiếm xe theo tên\033[0m                                           *");
            System.out.println("*                                           \033[1;31m5. Đăng xuất\033[0m                                                      *");
            System.out.println("****************************************************************************************************************");
            System.out.println("Nhập để chọn:");

            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showDetail();
                    break;
                case 2:
                    addToCart();
//                    new CartView().showListCart();
                    break;
                case 3:
                    new CartView().cartView();
                    break;
                case 4:
                    searchProductByName();
                    break;
                case 5:
                    logOut();
                    break;
                default:
                    System.err.println("Chọn không chính xác");
            }
        }
    }

    public void showListProduct() {
        for (Product product : productList) {
            System.out.printf("Mã xe          : %d \n", product.getProductId());
            System.out.printf("Tên xe         : %s \n", product.getProductName());
            System.out.printf("Giá xe         : %.1f \n", product.getPrice());
            System.out.println("Trạng thái xe : " + (product.isProductStatus() ? "Đang bán" : "Không bán \n"));
            System.out.println("----------------------------------------------------------");
        }
    }

    public void showDetail() {
        System.out.println("Nhập mã xe :");
        int id = InputMethods.getInteger();
        for (Product product : productList) {
            if (product.getProductId() == id) {
                product.displayDataProduct();
            }
        }
    }

    public void addToCart() {
        CartItem cartItem = new CartItem();
        System.out.println("Nhập mã xe :");
        int idCar = InputMethods.getInteger();
        System.out.println("Nhập số lượng :");
        cartItem.setQuantity(InputMethods.getInteger());
        if (cartItemList.size() == 0) {
            cartItem.setId(1);
        } else {
            cartItem.setId(cartItemList.get(cartItemList.size() - 1).getId() + 1);
        }
        Product product = productControl.findById(idCar);
        if (product == null || product.isProductStatus()==false) {
            System.err.println("Không thể thêm vào giỏ hàng");
            return;
        }else {
            cartItem.setProduct(product);
            cartItemController.createCartItem(cartItem);
        }

    }
    public void searchProductByName() {
        System.out.println("Nhập tên sản phẩm cần tìm kiếm:");
        String productName = InputMethods.getString();
        boolean found = false;

        for (Product product : productList) {
            if (product.getProductName().trim().toLowerCase().contains(productName.trim().toLowerCase())) {
                product.displayDataProduct();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sản phẩm với tên '" + productName + "'.");
        }
    }

    public void logOut() {
        userController.logOut();
        new Navbar().navbar();
    }
}
