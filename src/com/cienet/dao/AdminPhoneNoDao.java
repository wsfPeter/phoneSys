package com.cienet.dao;

import java.util.List;

import com.cienet.entity.AdminPhoneNo;

/**
 * @author daixun
 */
public interface AdminPhoneNoDao extends BaseDao<AdminPhoneNo, String> {
	
	

	/**
	 * 删除用户所拥有的手机号
	 * @param adminId
	 */
	public  void deleteAdminId(String adminId);
	
	/**
	 * 根据用户是否导出状态，删除用户手机号
	 * @param adminId
	 */
	public  AdminPhoneNo deleteAdminPhoneByStatu(String apnId);
	
	/**
	 * 获得用户所拥有的手机号
	 * @param adminId
	 */
	public  List<AdminPhoneNo> getAdminPhoneNo(String adminId);
	
	/**
	 * 获得手机号被哪些用户所持有
	 * @param adminId
	 */
	public  List<AdminPhoneNo> getPhoneNoAdmin(String phoneNoId);
	
}