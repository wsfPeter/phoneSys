package com.cienet.dao;

import java.util.List;

import com.cienet.bean.Pager;
import com.cienet.entity.PhoneNo;

/**
 * @author daixun
 */
public interface PhoneNoDao extends BaseDao<PhoneNo, String> {

	/**
	 * 手机号信息
	 * @param phoneNo
	 * @param phoneNoStatu
	 * @param statu
	 * @param username
	 * @return
	 */
	public Pager list(Pager pager,String phoneNo,String phoneNoStatu,String statu,String username);
	
	/**
	 * 获取未被分配的手机号
	 * @return
	 */
	public List<PhoneNo> getPhoneNoByStatu();
	
	/**
	 * 获取未被分配的手机号的个数
	 * @return
	 */
	public int getAllByStatu();
	
	/**
	 * 以指定个数获取未被分配的手机号
	 * @return
	 */
	public Object[] getLimitPhoneNo(int limit);
	
	/**
	 * 获取用户的资源统计情况
	 * @param adminId
	 * @return
	 */
	public List<Object> getResourceStatista(String adminId);
	
	/**
	 * 获取用户指定个数的手机号
	 * @param adminId 用户ID
	 * @param limit   获取的个数
	 * @return  机主姓名，手机号
	 */
	public  List<Object> getUserPhoneNo(String adminId,int limit);
	
}