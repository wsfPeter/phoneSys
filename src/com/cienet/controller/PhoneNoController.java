package com.cienet.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
import com.cienet.entity.PhoneNo;
import com.cienet.service.PhoneNoService;


@Controller
@RequestMapping(value = "/phoneNo")
public class PhoneNoController extends BaseController {

    @Autowired
    private PhoneNoService phoneNoService;
    
    private static final Logger log = Logger.getLogger(PhoneNoController.class);


    @InitBinder("phoneNo")
    public void binderPhoneNo(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("phoneNo.");
    }
    
    /**
    * 手机号列表
    */
   @RequestMapping("/list")
   public String list(Pager pager, Model model,String phoneNo,String phoneNoStatu,String statu) {
   	DetachedCriteria  criteria = DetachedCriteria.forClass(PhoneNo.class,"p");
   	if(StringUtils.isNotEmpty(phoneNo)){
   		criteria.add(Restrictions.like("p.phoneNo", "%"+phoneNo+"%"));
   	}
   	if(StringUtils.isNotEmpty(phoneNoStatu)){
   		criteria.add(Restrictions.eq("p.phoneNoStatu",phoneNoStatu));
   	}
   	if(StringUtils.isNotEmpty(statu)){
   		criteria.add(Restrictions.eq("p.statu",statu));
   	}
   	
	pager = phoneNoService.findByPager(pager,criteria);
    model.addAttribute("pager", pager);
    model.addAttribute("phoneNo", phoneNo);
    model.addAttribute("phoneNoStatu", phoneNoStatu);
    model.addAttribute("statu", statu);
    return "phone_list";
   }
   
   @RequestMapping("/add")
   public String add(Model model){
	   return "phone_add";
   }
   
   /**
   * 删除手机号
   */
  @RequestMapping("/delete")
  @ResponseBody
  public Message delete(String[] ids, HttpServletRequest request) {
      Message msg = new Message();
      try {
          phoneNoService.deletePhoneNo(ids);
          msg.setType("success");
          msg.setContent("操作成功");
      } catch (Exception e) {
    	  log.error("删除手机号失败.",e);
          msg.setType("error");
          msg.setContent("操作失败,该手机号下面包含用户！");
      }
      return msg;
  }


  /**
   * 添加手机号
   */
  @RequestMapping("save")
  public String save(@ModelAttribute PhoneNo phoneNo,Model model, HttpServletRequest request) {
	  phoneNoService.save(phoneNo);
	  log.info("添加手机号");
      model.addAttribute("url", "/phoneNo/list");
      return SUCCESS;
  }


    /**
     * 是否已存在 ajax验证
     * 
     * @param role
     * @return
     */
    @RequestMapping("/checkPhoneNo")
    @ResponseBody
    public Boolean checkPhoneNo(PhoneNo phoneNo, String oldValue) {
        if (StringUtils.isNoneEmpty(oldValue)
                && oldValue.equals(phoneNo.getPhoneNo())) {
            return true;
        }
        if (phoneNoService.isExist("phoneNo", phoneNo.getPhoneNo())) {
            return false;
        } else {
            return true;
        }
    }

 
    @RequestMapping("/edit")
    public String edit(String id, Model model) {
    	PhoneNo phoneNo = phoneNoService.get(id);
        model.addAttribute("phoneNo", phoneNo);
        return "phone_edit";
    }
    
    @RequestMapping("/detail")
    public String detail(Pager pager, Model model,String id,String username) {
       	DetachedCriteria  criteria = DetachedCriteria.forClass(PhoneNo.class,"p");
       	criteria.createAlias("admin", "a");
       	criteria.add(Restrictions.eq("p.id", id));
       	if(StringUtils.isNotEmpty(username)){
       		criteria.add(Restrictions.like("a.username", "%"+username+"%"));
       	}
           pager = phoneNoService.findByPager(pager,criteria);
           model.addAttribute("pager", pager);
           model.addAttribute("id",id);
           model.addAttribute("username", username);
           return "phone_detail";
       }

 
    /**
     * 修改手机号信息
     */
    @RequestMapping("/update")
    public String update(PhoneNo phoneNo, Model model,
            HttpServletRequest request) {
    	PhoneNo newPhoneNo = phoneNoService.get(phoneNo.getId());
    	newPhoneNo.setPhoneNo(phoneNo.getPhoneNo());
    	newPhoneNo.setPhoneName(phoneNo.getPhoneName());
    	try {
    		phoneNoService.update(newPhoneNo);
    		log.info("修改手机号");
		} catch (Exception e) {
			log.error("修改手机号出现错误.",e);
			 e.printStackTrace();
		}
    	 
        model.addAttribute("url", "/phoneNo/list");
        return SUCCESS;
    }
}
