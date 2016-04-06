package com.cienet.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cienet.dao.AdminDao;
import com.cienet.entity.Admin;

/**
 * Description: 管理员Dao实现类
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午09:46:17
 */
@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin, String> implements AdminDao {

	@Override
	public Admin loginInfo(String username, String pwd) {
		Query sql = getSession().createQuery("from Admin where username=:username and password=:pwd");
		sql.setString("username", username);
		sql.setString("pwd", pwd);
		return (Admin) sql.uniqueResult();
	}

	@Override
	public List<Object> getUserOPerate() {
		SQLQuery sql = getSession().createSQLQuery("SELECT a.id,a.username FROM admin_role ar,admin a,role r WHERE ar.admin_id=a.id AND ar.role_id = r.id AND r.name = '业务员' ");
		return sql.list();
	}
	
}