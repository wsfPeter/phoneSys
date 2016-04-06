package com.cienet.service;

import java.util.List;

import com.cienet.entity.Admin;

/**
 * Description: 管理员 Service接口
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午09:49:00
 */
public interface AdminService extends BaseService<Admin, String> {
	
	/**
	 * 登录信息接口.
	 * @param username 用户名
	 * @param pwd  密码
	 * @return  用户对象
	 */
	public Admin loginInfo(String username,String pwd);

    /**
     * 批量删除用户
     * @param ids 用户id
     * @return 被删除的用户名
     */
    public String deleteAdmin(String[] ids);

    /**
     * 获取当前登录管理员,若未登录则返回null.
     *
     * @return 当前登录管理员对象
     */
    public Admin getLoginAdmin();

    /**
     * 获取当前登录管理员(从数据库中加载),若未登录则返回null.
     *
     * @return 当前登录管理员对象
     */
    public Admin loadLoginAdmin();
    
    /**
	 * 获得所有的业务员
	 * @return
	 */
	public List<Object> getUserOPerate();

}