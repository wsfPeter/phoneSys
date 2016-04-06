package com.cienet.entity;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName: Admin
 * @Description: 管理员实体类
 * @author zhujiang
 * @date 2014年7月1日 下午7:59:45
 * 
 */
@Entity
@Table(name = "admin")
public class Admin extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 8206335717824266814L;
	/**
	 * @Fields name : 姓名
	 */
	private String name;

	/**
	 * @Fields username : 用户名
	 */
	private String username;

	/**
	 * @Fields password : 密码
	 */
	private String password;

	/**
	 * @Fields isAccountEnabled : 账号是否启用
	 */
	private Boolean isAccountEnabled = false;

	/**
	 * @Fields email : 邮箱
	 */
	private String email;

	/**
	 * @Fields isSystem : 是否为系统内置角色
	 */
	private Boolean isSystem = false;

	/**
	 * @Fields createDate : 创建时间
	 */
	private Date createDate = new Date();

	/**
	 * @Fields createDate : 最后登录时间
	 */
	private Date loginDate = new Date();

	/**
	 * @Fields roleSet : 角色列表
	 */
	private Set<Role> roleSet;

	/**
	 * @Fields authorities : 权限列表
	 */
	private Set<GrantedAuthority> authorities;

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Column(name = "createDate", updatable = false)
	public Date getCreateDate() {
		return createDate;
	}

	@Column(name = "email", length = 200)
	public String getEmail() {
		return email;
	}

	@Column(name = "is_account_enabled", nullable = false)
	public Boolean getIsAccountEnabled() {
		return isAccountEnabled;
	}

	@Column(name = "is_system", nullable = false, updatable = false)
	public Boolean getIsSystem() {
		return isSystem;
	}

	@Column(name = "loginDate")
	public Date getLoginDate() {
		return loginDate;
	}

	@Column(name = "name", length = 20, nullable = false)
	public String getName() {
		return name;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return password;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "admin_role", joinColumns = @JoinColumn(name = "admin_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@OrderBy("name asc")
	public Set<Role> getRoleSet() {
		return roleSet;
	}

	@Column(name = "username", updatable = false, nullable = false, unique = true, length = 50)
	public String getUsername() {
		return username;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	public boolean isEnabled() {
		return isAccountEnabled;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsAccountEnabled(Boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}