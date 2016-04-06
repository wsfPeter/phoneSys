package com.cienet.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.cienet.util.JsonUtil;

/**
 * Description: 角色实体类
 *
 * @author zhujiang
 * @version 1.0
 * @created 2013-3-21 下午04:42:21
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    public static final String ROLEBASE = "ROLE_BASE";
    private static final long serialVersionUID = 5391413982633656918L;
    private String name;// 角色名称
    private Boolean isSystem = false;// 是否为系统内置角色
    private String description;// 描述
    private String authorityList;// 权限列表
    private Set<Admin> adminSet;// 管理员

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleSet")
    public Set<Admin> getAdminSet() {
        return adminSet;
    }

    @SuppressWarnings("unchecked")
    @Transient
    public List<String> getAuthorities() {
        if (StringUtils.isEmpty(authorityList)) {
            return null;
        } else {
            if (!authorityList.contains("[")) {
                String[] authorities = authorityList.split(",");
                StringBuilder builder = new StringBuilder("[");
                int count = authorities.length;
                int size = 1;
                for (String str : authorities) {
                    builder.append("\"" + str.trim() + "\"");
                    if (size < count) {
                        builder.append(",");
                    }
                    size++;
                }
                builder.append("]");
                authorityList = builder.toString();
            }
            return (List<String>) JsonUtil.toObject(authorityList,
                    ArrayList.class);
        }
    }

    @Column(name = "authority_list", nullable = false)
    public String getAuthorityList() {
        return authorityList;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "is_system", nullable = false, updatable = false)
    public Boolean getIsSystem() {
        return isSystem;
    }

    @Column(name = "name", nullable = false, unique = true, length = 32)
    public String getName() {
        return name;
    }

    public void setAdminSet(Set<Admin> adminSet) {
        this.adminSet = adminSet;
    }

    public void setAuthorities(List<String> authorities) {
        if (authorities == null || authorities.size() == 0) {
            authorityList = null;
        } else {
            authorityList = JsonUtil.toString(authorities);
        }
    }

    public void setAuthorityList(String authorityList) {
        this.authorityList = authorityList;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public void setName(String name) {
        this.name = name;
    }
}