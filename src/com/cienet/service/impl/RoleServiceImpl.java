package com.cienet.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cienet.dao.RoleDao;
import com.cienet.entity.Role;
import com.cienet.service.RoleService;

/**
 * Description: 管理员Service实现类
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午09:49:48
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements
        RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public String deleteRole(String[] ids) {
        StringBuffer names = new StringBuffer();
        boolean flag = false;
        for (int i = 0; i < ids.length; i++) {
            Role role = roleDao.load(ids[i]);
            if (flag)
                names.append(",");
            names.append(role.getName());
            roleDao.delete(role);
            flag = true;
        }
        return names.toString();
    }

    @Resource
    public void setBaseDao(RoleDao adminDao) {
        super.setBaseDao(adminDao);
    }

}