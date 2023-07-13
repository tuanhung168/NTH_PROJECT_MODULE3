package ra.view;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.UserController;

public class AdminView {
    UserController userController = new UserController();
    public void adminView(){
        while (true){
            System.out.println("==========================\033[1;35mADMIN PAGE\033[0m================================");
            System.out.println("|               \033[1;31m1 . Đến trang quản lý người dùng\033[0m                   |");
            System.out.println("|               \033[1;33m2 . Đến trang quản lý sản phẩm\033[0m                     |");
            System.out.println("|               \033[1;32m3 . Quản lý lịch sử bán hàng\033[0m                       |");
            System.out.println("|               \033[1;34m4 . Đăng xuất\033[0m                                      |");
            System.out.println("====================================================================");
            System.out.println("Nhập để chọn");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    new UserManagement().userManagement();
                    break;
                case 2:
                    new ProductsManagement().productView();
                    break;
                case 3:
                    new PayHistory().payHistory();
                    break;
                case 4:
                    logOut();
                    break;
                default:
                    System.err.println("Chọn không chính xác!");
            }
        }
    }
    public void logOut(){
        userController.logOut();
        new Navbar().navbar();
    }
}
