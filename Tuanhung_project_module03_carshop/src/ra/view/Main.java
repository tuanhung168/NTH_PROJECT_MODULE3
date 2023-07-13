package ra.view;

import ra.config.Config;
import ra.controller.UserController;
import ra.model.Role;
import ra.model.RoleName;
import ra.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Config<User> userConfig = new Config<>();
        List<User> list = userConfig.readFormFile(Config.PATH_USER);
//        for (User u: list
//             ) {
//            System.out.println("ID "+u.getId() + "|username: "+u.getUserName() +"|passs: "+ u.getPassword()+"|status "+(u.getRole().getName().toString()));
//        }
//               User user = new User();
//        user.setId(1);
//        user.setUserName("admin");
//        user.setPassword("12345");
//        user.setRole(new Role(1,RoleName.ADMIN));
//        new UserController().register(user);
//        new UserManagement().showListUsers();
        new Navbar().navbar();
//        new AdminView().adminView();
//        new ProducersManagement().productView();
//        new ProductsManagement().productView();
    }
}
