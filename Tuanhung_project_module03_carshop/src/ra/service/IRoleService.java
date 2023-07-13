package ra.service;

import ra.model.Role;
import ra.model.RoleName;

import java.util.List;

public interface IRoleService {
    List<Role> findAll();
    Role findByName(RoleName name);
}
