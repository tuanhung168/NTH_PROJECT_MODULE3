package ra.service;

import ra.model.User;
import ra.service.IGeneric;

public interface IUserService extends IGeneric<User> {
    public boolean existedByUsername(String username);
    public boolean existedByEmail(String email);
    void changeRole(int id);
    void changeStatus(int id);
    boolean login(String userName,String password);
    User getCurrentUser();
    void logOut();
    boolean checkRole();
}
