package com.hibernate.dao;

import com.hibernate.model.Role;

public interface RoleDao {
    Role getRoleByName(String roleName);

    void add(Role role);
}
