package com.cienet.dao;

import java.util.List;

import com.cienet.entity.Admin;

/**
 * Description: 管理员Dao接口
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午09:45:34
 */
public interface AdminDao extends BaseDao<Admin, String> {

	/**
	 * 登录信息接口.
	 * @param username 用户名
	 * @param pwd  密码
	 * @return  用户对象
	 */
	public Admin loginInfo(String username,String pwd);
	
	/**
	 * 获得所有的业务员
	 * @return
	 */
	public List<Object> getUserOPerate();
	
}