package com.api.Films.service;

import com.api.Films.entity.Role;

public interface RoleService {
    Role saveRole(Role role);

    Role getRoleByName(String name);
}
