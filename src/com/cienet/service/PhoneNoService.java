package com.cienet.service;

import java.util.List;

import com.cienet.bean.Pager;
import com.cienet.entity.PhoneNo;

public interface PhoneNoService extends BaseService<PhoneNo, String> {
		
	 /**
     * 批量删除手机号
     * @param ids 手机id
     * @return 删除消息
     */
    public void deletePhoneNo(String[] ids);
    
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