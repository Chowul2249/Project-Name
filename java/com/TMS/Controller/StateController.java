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
import com.TMS.Entity.State;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.CountryService;
import com.TMS.serviceclass.StateService;



@RestController
public class StateController {
	
	@Autowired
	StateService stateService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;

	@RequestMapping(value="/state",method=RequestMethod.POST)
	public ModelAndView showstate(Model model)
	{		
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);	
		List<State> stateList = stateService.findAll();
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryListSize",countryList.size());
		model.addAttribute("edit_det", "No");
		return new ModelAndView("place/state/state"); 
	}
	@PostMapping(value="/cancelstate")
	public ModelAndView page(Model model)
	{
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);	
		List<State> stateList = stateService.findAll();
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryListSize",countryList.size());
		return new ModelAndView("place/state/state"); 
	}
	@ResponseBody
	@RequestMapping(value="/saveState",method=RequestMethod.POST)
	public ModelAndView saveState(@ModelAttribute State saveState,HttpServletRequest request,Model model){
		String return_status = "";
		int add_status = 0;
		
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);		
		
		saveState.getStateName();
		saveState.getStatus();
		saveState.getMappingCountryState();
		saveState.setCreationTime(creationTime);
		saveState.setIpAddress(ipAddress);
		saveState.setUser(12345);
		try
		{
			add_status=stateService.add(saveState);
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
		
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);	
		List<State> stateList = stateService.findAll();
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryListSize",countryList.size());
		model.addAttribute("edit_det", "No");
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
	//	return json;
		return new ModelAndView("place/state/state"); 
	}
	
	
	@RequestMapping(value="/editState",method=RequestMethod.POST)
	public ModelAndView editState(@RequestParam String id,Model model){
		

		String[] arr=id.split(",");
		
		int stateid=Integer.parseInt(arr[0].toString());
		int countryid=Integer.parseInt(arr[1].toString());
		
		State editObj = stateService.findById(stateid);
		model.addAttribute("editObj",editObj);		
		List<State> stateList = stateService.findAll();
		List<Country> countryList = countryService.findAll();
		model.addAttribute("countryList",countryList);
		model.addAttribute("countryid",countryid);
		model.addAttribute("stateList",stateList);
		model.addAttribute("edit_det", "Yes");
		model.addAttribute("countryListSize",countryList.size());
		return new ModelAndView("place/state/stateedit");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateState",method=RequestMethod.POST)
	public ModelAndView updateState(@ModelAttribute State updateState,HttpServletRequest request,Model model){
		
		String return_status = "";
		int add_status = 0;	
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
	
		
		updateState.getId();
		updateState.getMappingCountryState();
		updateState.getStateName();
		updateState.getStatus();
		updateState.setCreationTime(creationTime);
		updateState.setIpAddress(ipAddress);
		updateState.setUser(12345);
		try
		{
			add_status=stateService.update(updateState);
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
		
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);	
		List<State> stateList = stateService.findAll();
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryListSize",countryList.size());
		model.addAttribute("edit_det", "No");
		
		JSONObject json = new JSONObject();
		json.put("status", return_status);
		//return json;
		return new ModelAndView("place/state/state"); 
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteState")
	public ModelAndView deleteState(@RequestParam int id,Model model){	
		
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName", "Y");
		model.addAttribute("countryList",countryList);	
		List<State> stateList = stateService.findAll();
		model.addAttribute("stateList",stateList);
		model.addAttribute("countryListSize",countryList.size());
		model.addAttribute("edit_det", "No");
		
		 stateService.deleteById(id);
		return new ModelAndView("place/state/state"); 
		
	}
	
}
