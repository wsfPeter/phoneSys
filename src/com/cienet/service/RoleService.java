package com.cienet.service;

import com.cienet.entity.Role;

public interface RoleService extends BaseService<Role, String> {
    /**
     * 批量删除角色
     * @param ids 角色id
     * @return 角色名称
     */
    public String deleteRole(String[] ids);
}