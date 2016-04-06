package com.cienet.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cienet.bean.Pager;
import com.cienet.dao.PhoneNoDao;
import com.cienet.entity.PhoneNo;
import com.cienet.entity.Role;
import com.cienet.service.PhoneNoService;

/**
 * Description:手机号Service实现类
 * 
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-22 上午09:49:48
 */
@Service
public class PhoneNoServiceImpl extends BaseServiceImpl<PhoneNo, String> implements PhoneNoService {

	@Autowired
	private PhoneNoDao phoneNoDao;

	@Resource
	public void setBaseDao(PhoneNoDao phoneNoDao) {
		super.setBaseDao(phoneNoDao);
	}
	
	@Override
    public void deletePhoneNo(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
        	PhoneNo phoneNo = phoneNoDao.load(ids[i]);
        	phoneNoDao.delete(phoneNo);
        }
    }

	@Override
	public List<PhoneNo> getPhoneNoByStatu() {
		return phoneNoDao.getPhoneNoByStatu();
	}
	
	@Override
	public List<Object> getResourceStatista(String adminId) {
		return phoneNoDao.getResourceStatista(adminId);
	}

	@Override
	public Object[] getLimitPhoneNo(int limit) {
		return phoneNoDao.getLimitPhoneNo(limit);
	}

	@Override
	public int getAllByStatu() {
		return phoneNoDao.getAllByStatu();
	}

	@Override
	public List<Object> getUserPhoneNo(String adminId, int limit) {
		return phoneNoDao.getUserPhoneNo(adminId, limit);
	}

}