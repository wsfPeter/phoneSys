package com.cienet.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cienet.dao.AdminDao;
import com.cienet.entity.Admin;
import com.cienet.service.AdminService;

/**
 * Description: 管理员Service实现类
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午09:49:48
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, String> implements
        AdminService {

    @Resource
    private AdminDao adminDao;
    
    @Override
	public Admin loginInfo(String username, String pwd) {
		return adminDao.loginInfo(username, pwd);
	}

    @Override
    public String deleteAdmin(String[] ids) {
        StringBuffer names = new StringBuffer();
        boolean flag = false;
        for (int i = 0; i < ids.length; i++) {
            Admin admin = adminDao.load(ids[i]);
            if (flag)
                names.append(",");
            names.append(admin.getUsername());
            adminDao.delete(admin);
            flag = true;
        }
        return names.toString();
    }

    public Admin getLoginAdmin() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal == null || !(principal instanceof Admin)) {
            return null;
        } else {
            return (Admin) principal;
        }
    }

    public Admin loadLoginAdmin() {
        Admin admin = getLoginAdmin();
        if (admin == null) {
            return null;
        } else {
            return adminDao.load(admin.getId());
        }
    }

    @Resource
    public void setBaseDao(AdminDao adminDao) {
        super.setBaseDao(adminDao);
    }

	@Override
	public List<Object> getUserOPerate() {
		return adminDao.getUserOPerate();
	}


}