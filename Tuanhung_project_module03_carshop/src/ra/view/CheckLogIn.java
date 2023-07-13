package ra.view;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.UserController;

public class CheckLogIn {
    UserController userController = new UserController();
    public void checkLogIn(){
        while (true){
            System.out.println("1 . Đăng ký");
            System.out.println("2 . Quên mật khẩu");
            System.out.println("Nhập để chọn");
            int choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    new Navbar().register();
                    break;
                case 2:
                    rePassword();
                    break;
                default:
                    System.out.println("Nhập không chính xác!");
            }
        }
    }
    public void rePassword(){

        System.out.println("Nhập số điện thoại :");
        String phoneNumber = Config.scanner().nextLine();

    }
}
