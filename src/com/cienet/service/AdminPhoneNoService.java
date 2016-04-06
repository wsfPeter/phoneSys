package com.cienet.service;

import java.util.List;
import java.util.Map;

import com.cienet.entity.AdminPhoneNo;

public interface AdminPhoneNoService extends BaseService<AdminPhoneNo, String> {
	
	
	
	  /**
     * 批量删除用户手机
     * @param ids 用户手机id
     */
    public void deleteAdminId(String[] adminIds);
    
    /**
	 * 根据用户是否导出状态，删除用户手机号
	 * @param adminId
	 */
	public  AdminPhoneNo deleteAdminPhoneByStatu(String apnId);
    
    /**
     * 获得用户的手机号
     * @param ids 用户手机id
     */
    public List<AdminPhoneNo> getAdminPhoneNo(String adminId);
    
    /**
	 * 获得手机号被哪些用户所持有
	 * @param adminId
	 */
	public  List<AdminPhoneNo> getPhoneNoAdmin(String phoneNoId);
}