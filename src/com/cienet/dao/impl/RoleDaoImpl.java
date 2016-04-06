package com.cienet.dao.impl;

import org.springframework.stereotype.Repository;

import com.cienet.dao.RoleDao;
import com.cienet.entity.Role;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role, String> implements RoleDao {
}