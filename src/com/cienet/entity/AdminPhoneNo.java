package com.cienet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Description: 用户手机号码
 * 
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-21 下午04:42:21
 */
public class AdminPhoneNo extends BaseEntity {

	private static final long serialVersionUID = 7963625980026784835L;

	/** 管理员 */
	private Admin admin;

	/** 手机号码 */
	private PhoneNo phoneNo;

	/** 状态 默认为0，表示未导出 */
	private String statu = "0";

	public Admin getAdmin() {
		return admin;
	}
	public PhoneNo getPhoneNo() {
		return phoneNo;
	}

	public String getStatu() {
		return statu;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setPhoneNo(PhoneNo phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

}