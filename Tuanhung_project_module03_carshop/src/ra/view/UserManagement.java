package ra.view;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.UserController;
import ra.model.RoleName;
import ra.model.User;
import ra.service.UserServiceIMPL;

import java.util.List;

public class UserManagement {
    UserController userController = new UserController();
    UserServiceIMPL userServiceIMPL = new UserServiceIMPL();

    public void userManagement() {
        while (true) {
            System.out.println("========================\033[1;35mQUẢN LÝ NGƯỜI DÙNG\033[0m============================");
            System.out.println("|               \033[1;32m1 . Hiển thị danh sách người dùng\033[0m                    |");
            System.out.println("|               \033[1;33m2 . Thay đổi trạng thái người dùng\033[0m                   |");
            System.out.println("|               \033[1;36m3 . Cấp quyền!\033[0m                                       |");
            System.out.println("|               \033[1;34m4 . Tìm kiếm tên người dùng\033[0m                          |");
            System.out.println("|               \033[1;31m5 . Trở về Admin\033[0m                                     |");
            System.out.println("======================================================================");
            System.out.println("Nhập để chọn chức năng:");

            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    showListUsers();
                    break;
                case 2:
                    changeStatus();
                    break;
                case 3:
                    changeRole();
                    break;
                case 4:
                    searchUserName();
                    break;
                case 5:
                    new AdminView().adminView();
                    break;
                default:
                    System.err.println("Chọn không chính xác!");
            }
        }
    }

    public void changeStatus() {
        System.out.println("Nhập mã người dùng ");
        int id = InputMethods.getInteger();
        userController.changeStatus(id);
    }

    public void showListUsers() {
        List<User> listUser = userController.getListUser();
        for (User user : listUser) {
            user.displayUser();
            if (user.getRole().getName() == RoleName.USER) {
                System.out.println("===================CART====================");
                user.displayCart();
            }
        }
    }

    public void changeRole() {
        System.out.println("Nhập mã người dùng được cấp quyền ");
        int id = InputMethods.getInteger();
        userController.changeRole(id);
    }

    //    public void deleteUser(){
//        System.out.println("Nhập mã người dùng muốn xóa");
//        int idDel = Config.scanner().nextInt();
//        userController.deleteUser(idDel);
//    }
    public void searchUserName() {
        System.out.println("nhap ten");
        String name = InputMethods.getString();
        List<User> list = userServiceIMPL.searchUserName(name);
        if (list ==null){
            System.err.println("user trong");
        }else {
            System.out.println("-----------------------------------------------------------");
            for (User user: list) {
                System.out.println("Mã người dùng :"+ user.getId());
                System.out.println("Họ và tên     :"+user.getUserName());
                System.out.println("Email         :"+ user.getEmail());
                System.out.println("Số điện thoại :"+user.getPhoneNumber());
                System.out.println("Địa chỉ       :"+user.getAddress());
                System.out.println("Trạng thái    :"+(user.isUserStatus()?"Đang hoạt động":"Bị khoá"));
                System.out.println("Quyền         :"+user.getRole().getName());
            }
            System.out.println("------------------------------------------------------------");
        }
    }
}