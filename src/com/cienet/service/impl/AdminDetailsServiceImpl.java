package com.cienet.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cienet.dao.AdminDao;
import com.cienet.entity.Admin;
import com.cienet.entity.Role;

/**
 * Description: 后台权限认证Service实现类
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午10:19:44
 */
@SuppressWarnings("deprecation")
@Service
@Transactional
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AdminDao adminDao;

    // 获得管理角色
    public Set<GrantedAuthority> getGrantedAuthorities(Admin admin) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : admin.getRoleSet()) {
            for (String s : role.getAuthorities()) {
                grantedAuthorities.add(new GrantedAuthorityImpl(s));
            }
        }
        return grantedAuthorities;
    }

    public Admin loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        Admin admin = adminDao.get("username", username);
        if (admin == null) {
            throw new UsernameNotFoundException("管理员[" + username + "]不存在!");
        }
        admin.setAuthorities(getGrantedAuthorities(admin));
        return admin;
    }

}