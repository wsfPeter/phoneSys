package com.cienet.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cienet.dao.AdminPhoneNoDao;
import com.cienet.entity.AdminPhoneNo;
import com.cienet.service.AdminPhoneNoService;

/**
 * Description:用户手机号Service实现类
 * 
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午09:49:48
 */
@Service
public class AdminPhoneNoServiceImpl extends BaseServiceImpl<AdminPhoneNo, String> implements AdminPhoneNoService {

	@Autowired
	private AdminPhoneNoDao adminPhoneNoDao;

	@Resource
	public void setBaseDao(AdminPhoneNoDao adminPhoneNoDao) {
		super.setBaseDao(adminPhoneNoDao);
	}
	
	@Override
    public void deleteAdminId(String[] adminIds) {
		for(String aid : adminIds){
			adminPhoneNoDao.deleteAdminId(aid);
		}
    }

	@Override
	public List<AdminPhoneNo> getAdminPhoneNo(String adminId) {
		return adminPhoneNoDao.getAdminPhoneNo(adminId);
	}

	@Override
	public List<AdminPhoneNo> getPhoneNoAdmin(String phoneNoId) {
		return adminPhoneNoDao.getPhoneNoAdmin(phoneNoId);
	}


	@Override
	public AdminPhoneNo deleteAdminPhoneByStatu(String apnId) {
		return adminPhoneNoDao.deleteAdminPhoneByStatu(apnId);
	}

}