package com.hibernate.service;

import com.hibernate.model.Role;

public interface RoleService {
    void add(Role role);

    Role getRoleByName(String roleName);
}
