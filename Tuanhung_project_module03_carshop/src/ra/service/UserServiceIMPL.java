package ra.service;

import ra.config.Config;
import ra.model.Role;
import ra.model.RoleName;
import ra.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService {
    List<User> userList = new Config<User>().readFormFile(Config.PATH_USER);
    List<User> userLoginList = new Config<User>().readFormFile(Config.PATH_USERLOGIN);

    @Override
    public List<User> fileAll() {
        return userList;
    }

    @Override
    public void save(User user) {
        if (findById(user.getId()) == null) {
            userList.add(user);
        } else {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getId() == user.getId()) {
                    userList.set(i, user);
                }
            }
        }
        new Config<User>().writeFormFile(Config.PATH_USER, userList);
    }

    @Override
    public User findById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                userList.remove(user);
            }
        }
        new Config<User>().writeFormFile(Config.PATH_USER, userList);
    }
    public List<User> searchUserName (String name){
        List<User> userList1 =new ArrayList<>();
        for (User user: userList) {
            if (user.getUserName().contains(name)){
                userList1.add(user);
            }
        }
        return userList1;
    }

    @Override
    public boolean existedByUsername(String username) {
        for (User user : userList) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail() != null && user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void changeRole(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                user.setRole(new Role(1, RoleName.ADMIN));
                new Config<User>().writeFormFile(Config.PATH_USER, userList);
            }
        }
    }

    @Override
    public void changeStatus(int id) {
        boolean flag = false;
        for (User user : userList) {
            if (user.getId() == id) {
                if (user.isUserStatus()){
                    user.setUserStatus(false);
                    System.out.println("block thanh cong");
                    flag = true;
                }else {
                    user.setUserStatus(true);
                    System.out.println("unlock thanh cong");
                    flag = true;
                }
                new Config<User>().writeFormFile(Config.PATH_USER, userList);
            }
        }
        if (!flag){
            System.err.println("id khong hop le");
            System.out.println("");
        }
    }

    @Override
    public boolean login(String userName, String password) {
        for (User user : userList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                if (userLoginList.isEmpty()){
                    userLoginList.add(user);}
                else {
                    userLoginList.set(0,user);
                }
                new Config<User>().writeFormFile(Config.PATH_USERLOGIN, userLoginList);
                return true;
            }
        }
        return false;
    }

    public void logOut() {
        userLoginList.remove(0);
        new Config<User>().writeFormFile(Config.PATH_USERLOGIN, userLoginList);
    }

    @Override
    public boolean checkRole() {
        User user = getCurrentUser();
        if (user.getRole().getName() == RoleName.ADMIN) {
            return true;
        }
        return false;
    }

    @Override
    public User getCurrentUser() {
        if (userLoginList.size() != 0) {
            return userLoginList.get(0);
        }
        return null;
    }

}
