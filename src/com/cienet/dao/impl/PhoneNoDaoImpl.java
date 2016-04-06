package com.cienet.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.cienet.bean.Pager;
import com.cienet.dao.PhoneNoDao;
import com.cienet.entity.PhoneNo;

@Repository
public class PhoneNoDaoImpl extends BaseDaoImpl<PhoneNo, String> implements PhoneNoDao {

	public List<PhoneNo> getPhoneNoByStatu() {
		Query query = getSession().createQuery("from PhoneNo where phoneNoStatu=:statu");
		query.setString("statu","0");
		return query.list();
	}

	@Override
	public List<Object> getResourceStatista(String adminId) {
		SQLQuery sql = getSession().createSQLQuery("SELECT COUNT(*) allPhoneNo,(SELECT COUNT(*)  FROM phoneno WHERE aid=:aid AND statu='1') doneDownload FROM  phoneno p WHERE p.aid =:aid");
		sql.setString("aid", adminId);
		return sql.list();
	}

	@Override
	public Object[] getLimitPhoneNo(int limit) {
		SQLQuery sql = getSession().createSQLQuery("select id from phoneno where phoneNoStatu = '0' limit :limit");
		sql.setInteger("limit", limit);
		return sql.list().toArray();
	}

	@Override
	public int getAllByStatu() {
		SQLQuery sql = getSession().createSQLQuery("select count(*) from phoneNo where phoneNoStatu='0'");
		return Integer.parseInt(sql.uniqueResult().toString());
	}
	
	@Override
	public List<Object> getUserPhoneNo(String adminId, int limit) {
		SQLQuery sql = getSession().createSQLQuery("SELECT p.id,p.phoneName,p.phoneno FROM phoneno p,admin a WHERE  p.aid=a.id AND  a.id=:aid AND P.statu='0'  LIMIT :count");
		sql.setString("aid", adminId);
		sql.setInteger("count", limit);
		List  datas = sql.list();
		
		HashMap<String,Object> phones = null;
		PhoneNo p = null;
		List<Object> userPhoneNos = new ArrayList<Object>();
		for(Object obj : datas){
			Object[] objArr = (Object[]) obj;
			phones = new HashMap<String,Object>();
			
			//导出手机号，更改状态
			p = this.load((String)objArr[0]);
			p.setStatu("1");
	        this.update(p);
			
			phones.put("phoneNo",objArr[2]);
			phones.put("phoneName",objArr[1]);
			userPhoneNos.add(phones);
		}
		getSession().flush();
		
		return userPhoneNos;
	}

	@Override
	public Pager list(Pager pager,String phoneNo, String phoneNoStatu, String statu, String username) {
		if(pager == null){
			pager = new Pager();
		}
		
		Integer pageNumber = pager.getPageNumber();
		Integer pageSize = pager.getPageSize();
		
		String sql = "SELECT  p.id,p.phoneName,p.phoneno,a.username,a.name,p.phoneNoStatu,p.statu FROM phoneno p LEFT JOIN admin a ON( a.id=p.aid) where 1=1 ";
		
		if(StringUtils.isNotEmpty(phoneNo)){
			sql +=" and p.phoneno like '%"+phoneNo+"%'";
		}
		
		if(StringUtils.isNotEmpty(username)){
			sql +=" and a.username like '%"+username+"%'";
		}
		
		if(StringUtils.isNotEmpty(statu)){
			sql +=" and p.statu = '"+statu+"'";
		}
		
		if(StringUtils.isNotEmpty(phoneNoStatu)){
			sql +=" and p.phoneNoStatu = '"+phoneNoStatu+"'";
		}
		
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		sqlQuery.setFirstResult((pageNumber - 1) * pageSize);
		sqlQuery.setMaxResults(pageSize);
		//pager.setTotalCount(Long.parseLong(totalCount));
		pager.setResult(sqlQuery.list());
		return pager;
	}

	
}