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

import com.TMS.Entity.PrintCategory;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.PrintCategoryService;

@Controller
public class PrintCategoryController {
	
	@Autowired
	PrintCategoryService printCategoryService;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;

	@RequestMapping(value="/printCategory",method=RequestMethod.POST)
	public ModelAndView printCategory(Model model)
	{
		
		model.addAttribute("edit_det", "No");
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		model.addAttribute("printCategoryListSize",printCategory.size());
		return new ModelAndView("printcategory/PrintCategory");
		
	} 
	@PostMapping(value="/PrintCategoryPage")
	public ModelAndView page(Model model)
	{
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		model.addAttribute("printCategoryListSize",printCategory.size());
		return new ModelAndView("printcategory/PrintCategory");
	}
	
	@ResponseBody
	@RequestMapping(value="/savePrintCategory",method=RequestMethod.POST)
	public ModelAndView savePrintCategory(Model model,@ModelAttribute PrintCategory savePrintCategory,HttpServletRequest request)
	{
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		
		savePrintCategory.getPrintCat_name();
		savePrintCategory.getStatus();
		
		savePrintCategory.setIpAddress(ipAddress);
		savePrintCategory.setTimeStamp(creationTime);
		savePrintCategory.setUser(12345);
		
		try {
			add_status=printCategoryService.save(savePrintCategory);
			if(add_status>0)
				return_status="OK";
			
			
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
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		
		
		model.addAttribute("edit_det", "No");
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		model.addAttribute("printCategoryListSize",printCategory.size());
		return new ModelAndView("printcategory/PrintCategory");
		
	}
	
	@RequestMapping(value="/editPrintCategory",method=RequestMethod.POST)
	public ModelAndView editPrintCategory(Model model,@RequestParam int id){
		PrintCategory editObj = printCategoryService.findById(id);
		model.addAttribute("editObj",editObj);
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		model.addAttribute("printCategoryListSize",printCategory.size());
		
		model.addAttribute("edit_det", "Yes");
		return new ModelAndView("printcategory/PrintCategoryEdit");
	}
	
	@ResponseBody
	@RequestMapping(value="/updatePrintCategory",method=RequestMethod.POST)
	public ModelAndView updatePrintCategory(Model model,@ModelAttribute PrintCategory updatePrintCategory,HttpServletRequest request)
	{
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		@SuppressWarnings("unused")
		PrintCategory saveObj=new PrintCategory();
		updatePrintCategory.getPrintCat_name();
		updatePrintCategory.getStatus();
		updatePrintCategory.setIpAddress(ipAddress);
		updatePrintCategory.setTimeStamp(creationTime);
		updatePrintCategory.setUser(12345);
		updatePrintCategory.getId();
		
		try {
			add_status=printCategoryService.update(updatePrintCategory);
			if(add_status>0)
				return_status="OK";
			
			
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
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		
		model.addAttribute("edit_det", "No");
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		model.addAttribute("printCategoryListSize",printCategory.size());
		return new ModelAndView("printcategory/PrintCategory");
	}
	
	@ResponseBody
	@RequestMapping(value="/deletePrintCategory")
	public ModelAndView deletePrintCategory(@RequestParam int id,Model model){
		
	//	PrintCategory departmentObj=printCategoryService.findById(id);
		model.addAttribute("edit_det", "No");
		List<PrintCategory> printCategory=printCategoryService.findAll();
		model.addAttribute("printCategoryList", printCategory);
		model.addAttribute("printCategoryListSize",printCategory.size());
		
		printCategoryService.deleteById(id);
		return new ModelAndView("printcategory/PrintCategory");
		
	}

}
