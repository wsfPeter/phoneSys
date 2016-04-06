package com.cienet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Description: 手机号码
 * 
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-21 下午04:42:21
 */
@Entity
@Table(name = "phoneno")
public class PhoneNo extends BaseEntity {

	private static final long serialVersionUID = 508313188823402291L;

	/** 手机号 */
	private String phoneNo;

	/** 业务员 */
	private Admin admin;

	/** 机主名 */
	private String phoneName;

	/** 手机号分配状态 */
	private String phoneNoStatu = "0";

	/** 手机号导出状态 */
	private String statu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aid", nullable = true)
	public Admin getAdmin() {
		return admin;
	}

	@Column(name = "phoneNo", nullable = false, length = 11, unique = true)
	public String getPhoneNo() {
		return phoneNo;
	}

	@Column(name = "phoneName", length = 20)
	public String getPhoneName() {
		return phoneName;
	}

	@Column(name = "phoneNoStatu", length = 1)
	public String getPhoneNoStatu() {
		return phoneNoStatu;
	}

	@Column(name = "statu", length = 1)
	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setPhoneNoStatu(String phoneNoStatu) {
		this.phoneNoStatu = phoneNoStatu;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}