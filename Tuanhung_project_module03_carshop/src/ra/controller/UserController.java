package ra.controller;


import Response.ResponseMessage;
import ra.model.User;
import ra.service.IRoleService;
import ra.service.RoleServiceIMPL;
import ra.service.IUserService;
import ra.service.UserServiceIMPL;

import java.util.List;

public class UserController {
    IRoleService roleService = new RoleServiceIMPL();
    IUserService userService = new UserServiceIMPL();
    public ResponseMessage register(User user){
        if (userService.existedByUsername(user.getUserName())){
            return new ResponseMessage("userName existed");
        } else if (userService.existedByEmail(user.getEmail())) {
            return new ResponseMessage("email existed");
        }
        userService.save(user);
        return new ResponseMessage("register successful");
    }
    public boolean login(String userName,String password){
        return userService.login(userName,password);
    }
    public void logOut(){
        userService.logOut();
    }
    public List<User> getListUser(){
        return userService.fileAll();
    }
    public User getUserLogin(){ return userService.getCurrentUser();}
    public User findById(int id){
        return userService.findById(id);
    }
    public void editUser(User user){
        userService.save(user);
    }
    public void deleteUser(int id){
        userService.deleteById(id);
    }
    public void changeRole(int id){
        userService.changeRole(id);
    }
    public void changeStatus(int id){
        userService.changeStatus(id);
    }
    public boolean checkRole(){
        return userService.checkRole();
    }
}
