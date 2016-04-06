package com.cienet.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cienet.bean.Message;
import com.cienet.bean.Pager;
import com.cienet.entity.Role;
import com.cienet.service.RoleService;
import com.cienet.util.JsonUtil;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
    private Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色跳转
     */
    @RequestMapping("/add")
    public String add() {
        return "role_add";
    }

    @InitBinder("role")
    public void binderRole(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("role.");
    }

    /**
     * 是否已存在 ajax验证
     * 
     * @param role
     * @return
     */
    @RequestMapping("/checkName")
    @ResponseBody
    public Boolean checkName(Role role, String oldValue) {
        if (StringUtils.isNoneEmpty(oldValue)
                && oldValue.equals(role.getName())) {
            return true;
        }
        if (roleService.isExist("name", role.getName())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Message delete(String[] ids, HttpServletRequest request) {
        Message msg = new Message();
        try {
            String names = roleService.deleteRole(ids);
            msg.setType("success");
            msg.setContent("操作成功");
            isRecordLog = true;
            operationName = "删除角色";
            logInfo = "删除角色：" + names;
            setLog(request);
        } catch (Exception e) {
            log.error("角色删除异常", e);
            msg.setType("error");
            msg.setContent("操作失败,该角色下面包含管理员！");
        }
        return msg;
    }

    @RequestMapping("/edit")
    public String edit(String id, Model model) {
        Role role = roleService.get(id);
        model.addAttribute("role", role);
        model.addAttribute("id", id);
        return "role_edit";
    }

    /**
     * 角色列表
     */
    @RequestMapping("/list")
    public String list(Pager pager, Model model,String roleName) {
    	DetachedCriteria  criteria = DetachedCriteria.forClass(Role.class);
    	if(StringUtils.isNotEmpty(roleName)){
    		criteria.add(Restrictions.like("name", "%"+roleName+"%"));
    	}
        pager = roleService.findByPager(pager,criteria);
        model.addAttribute("pager", pager);
        model.addAttribute("roleName", roleName);
        return "role_list";
    }

    /**
     * 添加新角色
     */
    @RequestMapping("save")
    public String save(@ModelAttribute Role role, String[] authority,
            Model model, HttpServletRequest request) {
        role.setAuthorityList(JsonUtil.toString(authority));
        roleService.save(role);
        isRecordLog = true;
        operationName = "新增角色";
        logInfo = "新增角色：" + role.getName();
        setLog(request);
        model.addAttribute("url", "/role/list");
        return SUCCESS;
    }

    /**
     * 修改角色信息
     */
    @RequestMapping("/update")
    public String update(Role role, String[] authority, Model model,
            HttpServletRequest request) {
        Role rol = roleService.get(role.getId());
        BeanUtils.copyProperties(role, rol, new String[] { "id",
                "authorityList", "adminSet" });
        rol.setAuthorityList(JsonUtil.toString(authority));
        roleService.update(rol);
        isRecordLog = true;
        operationName = "修改角色";
        logInfo = "修改角色：" + rol.getName();
        setLog(request);
        model.addAttribute("url", "/role/list");
        return SUCCESS;
    }
}
