package com.cienet.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cienet.dao.AdminPhoneNoDao;
import com.cienet.entity.AdminPhoneNo;

@Repository
public class AdminPhoneNoDaoImpl extends BaseDaoImpl<AdminPhoneNo, String> implements AdminPhoneNoDao {
	
	@Override
	public void deleteAdminId(String adminId) {
		  SQLQuery  sql = getSession().createSQLQuery("DELETE FROM admin_phone WHERE admin_id = '"+adminId+"'");
		  sql.executeUpdate();
	}
	
	@Override
	public AdminPhoneNo deleteAdminPhoneByStatu(String apnId) {
		 AdminPhoneNo apn = null;
		  SQLQuery  sql = getSession().createSQLQuery("DELETE FROM admin_phone WHERE id = '"+apnId+"' and statu='0'");
		  if(sql.executeUpdate() > 0){
			  apn =  load(apnId);
		  }
		  return apn ;
	}


	@Override
	public List<AdminPhoneNo> getAdminPhoneNo(String adminId) {
		Query query = getSession().createQuery("from AdminPhoneNo where admin_id=:aid");
		query.setString("aid", adminId);
		 return query.list();
	}

	@Override
	public List<AdminPhoneNo> getPhoneNoAdmin(String phoneNoId) {
		Query query = getSession().createQuery("from AdminPhoneNo where phone_id=:pid");
		query.setString("pid", phoneNoId);
		 return query.list();
	}

}