package com.TMS.Controller;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.TMS.Entity.Country;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.CountryService;



@RestController
public class CountryController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
	
	@RequestMapping(value="/countryPage",method=RequestMethod.POST)
	public ModelAndView countryPage(Model model)
	{
		
		List<Country> countryList = countryService.findAll();
		model.addAttribute("countryList",countryList);
		model.addAttribute("countryListSize",countryList.size());
		return new ModelAndView("place/Country/Country");
	}
	@PostMapping(value="/cancelcountry")
	public ModelAndView cancelpage(Model model)
	{
		
		List<Country> countryList = countryService.findAll();
		model.addAttribute("countryList",countryList);
		model.addAttribute("countryListSize",countryList.size());
		return new ModelAndView("place/Country/Country");
	}
	
	@ResponseBody
	@RequestMapping(value="/saveCountry",method=RequestMethod.POST)
	public ModelAndView saveCountry(
			@ModelAttribute Country saveCountry,
			HttpServletRequest request,
			Model model){
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
			
		saveCountry.getCountryName();
		saveCountry.getStatus();
		saveCountry.setIpAddress(ipAddress);
		saveCountry.setCreationTime(creationTime);
		saveCountry.setUser(1234);	
		
		
		
  	  try
	      {	        
		     add_status = countryService.save(saveCountry);
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
  	  
  	  

		List<Country> countryList = countryService.findAll();
		model.addAttribute("countryList",countryList);
		model.addAttribute("countryListSize",countryList.size());
		model.addAttribute("edit_det", "No");
  	  
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("place/Country/Country");
	}

	
	@RequestMapping(value="/editCountry",method=RequestMethod.POST)
	public ModelAndView editCountry(Model model,@RequestParam int id){
		Country editObj = countryService.findById(id);
		model.addAttribute("editObj",editObj);
		List<Country> countryList = countryService.findAll();
		model.addAttribute("countryListSize",countryList.size());
		model.addAttribute("countryList",countryList);
		model.addAttribute("edit_det", "Yes");
		return new ModelAndView("place/Country/countryedit");
	}
	
	
	@ResponseBody
	@RequestMapping(value="/UpdateCountry",method=RequestMethod.POST)
	public ModelAndView updateCountry(@ModelAttribute Country updateCountry,HttpServletRequest request,Model model){
		String return_status = "";
		int add_status = 0;
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		updateCountry.getId();
		updateCountry.getCountryName();
		updateCountry.getStatus();
		updateCountry.setIpAddress(ipAddress);
		updateCountry.setCreationTime(creationTime);
		updateCountry.setUser(1234);		
		
  	  try
	      {
		     add_status = countryService.update(updateCountry);		     
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
		
		
  	List<Country> countryList = countryService.findAll();
	model.addAttribute("countryList",countryList);
	model.addAttribute("countryListSize",countryList.size());
	model.addAttribute("edit_det", "No");
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("place/Country/Country");
	}
	
	
	@RequestMapping(value="/deleteCountry")
	public ModelAndView deleteCountry(@RequestParam int id,Model model){
		
		countryService.deleteById(id);
		
		List<Country> countryList = countryService.findAll();
		model.addAttribute("countryList",countryList);
		model.addAttribute("countryListSize",countryList.size());
		model.addAttribute("edit_det", "No");
		return new ModelAndView("place/Country/Country");
	
	}
}
