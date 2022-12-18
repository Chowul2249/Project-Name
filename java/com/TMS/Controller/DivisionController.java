package com.TMS.Controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.Department;
import com.TMS.Entity.Division;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.DepartmentService;
import com.TMS.serviceclass.DivisionService;

@Controller
public class DivisionController {
	
	@Autowired 
	DepartmentService departmentservice;
	
	@Autowired
	DivisionService divisionservice;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
	
	@RequestMapping(value="/division",method=RequestMethod.POST)
	public ModelAndView division(Model model)
	{		
		List<Department> departmentList = departmentservice.findAllByAscendingOrder("depart_name", "Y");
		model.addAttribute("departmentList",departmentList);
		List<Division> divisionList = divisionservice.findAll();
		model.addAttribute("divisionList",divisionList);
		
		model.addAttribute("divisionListSize",divisionList.size());
		model.addAttribute("edit_det", "No");
		return new ModelAndView("Division/division"); 
	}
	@PostMapping(value="/canceldivision")
	public ModelAndView page(Model model)
	{
		List<Department> departmentList = departmentservice.findAllByAscendingOrder("depart_name", "Y");
		model.addAttribute("departmentList",departmentList);
		List<Division> divisionList = divisionservice.findAll();
		model.addAttribute("divisionList",divisionList);
		
		model.addAttribute("divisionListSize",divisionList.size());
		model.addAttribute("edit_det", "No");
		return new ModelAndView("Division/division"); 
	}
	@ResponseBody
	@RequestMapping(value="/saveDivision",method=RequestMethod.POST)
	public ModelAndView saveDivision(@ModelAttribute Division saveDivision,HttpServletRequest request,Model model){
		String return_status = "";
		int add_status = 0;
	
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		
		saveDivision.setDepartmentToDivision(saveDivision.getDepartmentToDivision());
		saveDivision.getDivision_name();
		saveDivision.getStatus();
		saveDivision.setDivision_time(creationTime);
		saveDivision.setDivision_user(12345);
		saveDivision.setDepartmenttodiv(add_status); 
		saveDivision.setDivision_ip(ipAddress);
		
		try
		{
			add_status=divisionservice.add(saveDivision);
		 if (add_status > 0)
				return_status = "OK";
		}
		catch (ConstraintViolationException dive) {
			return_status = "Data Integrity Exception";
		} catch (DataIntegrityViolationException cve) {
			return_status = "Data already present please check it!";
		} catch (HibernateException he) {
			return_status = "Fill The form Properly!";
		} catch (Exception e) {
			return_status = "Sql Exception has been caught";
		}
		
		List<Department> departmentList = departmentservice.findAllByAscendingOrder("depart_name", "Y");
		model.addAttribute("departmentList",departmentList);
		List<Division> divisionList = divisionservice.findAll();
		model.addAttribute("divisionList",divisionList);
		
		model.addAttribute("divisionListSize",divisionList.size());
		model.addAttribute("edit_det", "No");
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("Division/division"); 
	}
	
	
	@RequestMapping(value="/editDivision",method=RequestMethod.POST)
	public ModelAndView editDivision(@RequestParam String id,Model model){
		
		
		
		String[] arr=id.split(",");
		
		int divid=Integer.parseInt(arr[0].toString());
		int deptid=Integer.parseInt(arr[1].toString());
		
		
		
		Division Div = divisionservice.findById(divid);
		model.addAttribute("div",Div);	
		
		/*Department Depart = departmentservice.findById(deptid);
		model.addAttribute("dept",deptid);
		*/
		List<Division> divisionList = divisionservice.findAll();
		List<Department> departmentList = departmentservice.findAll();
		model.addAttribute("departmentList",departmentList);
		model.addAttribute("dept",deptid);
		model.addAttribute("divisionList",divisionList);
		model.addAttribute("edit_det", "Yes");
		model.addAttribute("divisionListSize",divisionList.size());
		return new ModelAndView("Division/divisionedit");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateDivision",method=RequestMethod.POST)
	public ModelAndView updateDivision(@ModelAttribute Division div,HttpServletRequest request,Model model){
		
		String return_status = "";
		int add_status = 0;	
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		@SuppressWarnings("unused")
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
	
		//Department departmentName= departmentservice.findById(departmentId);
		
		@SuppressWarnings("unused")
		Division updateObj= new Division();
		div.getId();
		div.getDivision_name();
		div.getStatus();
		div.getDepartmentToDivision();
		div.setDivision_time(creationTime);
		div.setDivision_user(12345);
		try
		{
			divisionservice.update(div);
		if (add_status > 0)
			return_status = "OK";
		}
		catch (ConstraintViolationException dive) {
			return_status = "Data Integrity Exception";
		} catch (DataIntegrityViolationException cve) {
			return_status = "Data already present please check it!";
		} catch (HibernateException he) {
			return_status = "Fill The form Properly!";
		} catch (Exception e) {
			return_status = "Sql Exception has been caught";
		}
		
		List<Department> departmentList = departmentservice.findAllByAscendingOrder("depart_name", "Y");
		model.addAttribute("departmentList",departmentList);
		List<Division> divisionList = divisionservice.findAll();
		model.addAttribute("divisionList",divisionList);
		
		model.addAttribute("divisionListSize",divisionList.size());
		model.addAttribute("edit_det", "No");
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("Division/division"); 
		
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteDivision")
	public ModelAndView deleteDivision(@RequestParam int id,Model model){	
		
		List<Department> departmentList = departmentservice.findAllByAscendingOrder("depart_name", "Y");
		model.addAttribute("departmentList",departmentList);
		List<Division> divisionList = divisionservice.findAll();
		model.addAttribute("divisionList",divisionList);
		
		model.addAttribute("divisionListSize",divisionList.size());
		model.addAttribute("edit_det", "No");
		
		 divisionservice.deleteById(id);
		return new ModelAndView("Division/division"); 
		
	}
}
