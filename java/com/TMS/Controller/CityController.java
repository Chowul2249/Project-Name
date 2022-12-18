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

import com.TMS.Entity.City;
import com.TMS.Entity.Country;
import com.TMS.Entity.State;
import com.TMS.common.OrgCommonUtilityDao;
import com.TMS.serviceclass.CityService;
import com.TMS.serviceclass.CountryService;
import com.TMS.serviceclass.StateService;



@RestController
public class CityController {
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	StateService stateService;
	
	@Autowired
	CityService cityService;
	   
	@Autowired
	OrgCommonUtilityDao orgCommonUtilityDao;
	
	
	
	@RequestMapping(value="/city",method=RequestMethod.POST)
	public ModelAndView city(Model model)
	{
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName","Y");
		List<City> cityList = cityService.findAll();
		List<State> stateList=stateService.findAllByAscendingOrder("stateName", "Y");	
		model.addAttribute("printstateList", stateList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("cityListSize",cityList.size());
		model.addAttribute("edit_det", "No");
		model.addAttribute("countryList",countryList);	
			
		return new ModelAndView("place/city/city");
	}
	@PostMapping(value="/cancelcity")
	public ModelAndView page(Model model)
	{
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName","Y");
		List<City> cityList = cityService.findAll();
		List<State> stateList=stateService.findAllByAscendingOrder("stateName", "Y");	
		model.addAttribute("printstateList", stateList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("cityListSize",cityList.size());
		model.addAttribute("edit_det", "No");
		model.addAttribute("countryList",countryList);
		return new ModelAndView("place/city/city");
	}
	@RequestMapping(value="/loadStatebyCountry")
	public ModelAndView loadStatebyCountry(@RequestParam int countryID,Model model){
		System.out.println(countryID);
		List<State> stateList=stateService.getListbyCountryID(countryID);	
		model.addAttribute("stateList", stateList);
		System.out.println(stateList.size());
		return new ModelAndView("place/city/city");
	}
	
	
	@ResponseBody
	@RequestMapping(value="/saveCity",method=RequestMethod.POST)
	public ModelAndView saveCity(HttpServletRequest request,Model model,@ModelAttribute City saveCity){
		String return_status = "";
		int add_status = 0;
		
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		
		
		saveCity.getMappingCountryCity();
		saveCity.getMappingStateCity();
		saveCity.getCityName();
		saveCity.getStatus();
		saveCity.getLocation();
		saveCity.getPopulation();
		if(saveCity.getRemoteLocation()!=null)
		{
			saveCity.getRemoteLocation();
		}
		else
		{
			saveCity.setRemoteLocation("N");
		}
		
		
		saveCity.setIpAddress(ipAddress);
		saveCity.setCreationTime(creationTime);
		saveCity.setUser(12345);
		
		try
		{
			add_status=cityService.add(saveCity);
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
		
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName","Y");
		List<City> cityList = cityService.findAll();
		List<State> stateList=stateService.findAllByAscendingOrder("stateName", "Y");	
		model.addAttribute("printstateList", stateList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("cityListSize",cityList.size());
		model.addAttribute("edit_det", "No");
		model.addAttribute("countryList",countryList);	
		
		JSONObject json = new JSONObject();
		json.put("return_status", return_status);
		//return json;
		return new ModelAndView("place/city/city");
	}
	
	@RequestMapping(value="/editCity",method=RequestMethod.POST)
	public ModelAndView editCity(@RequestParam int id,
			Model model){
		City editObj = cityService.findById(id);
		model.addAttribute("editObj", editObj);	
		int country=editObj.getMappingCountryCity();
		int state=editObj.getMappingStateCity();
		
		List<Country> countryList = countryService.findAll();
		List<State> stateList = stateService.findAll();
		List<City> cityList = cityService.findAll();
		model.addAttribute("cityList",cityList);
		model.addAttribute("countryList",countryList);
		model.addAttribute("country", country);
		model.addAttribute("edit_det", "Yes");
		model.addAttribute("stateList",stateList);
		model.addAttribute("state", state);
		model.addAttribute("cityListSize",cityList.size());
		return new ModelAndView("place/city/cityedit");
	}
	
	@ResponseBody
	@RequestMapping(value="/updateCity",method=RequestMethod.POST)
	public ModelAndView updateCity(HttpServletRequest request,Model model,@ModelAttribute City updateCity ){
		String return_status = "";
		int add_status = 0;
		System.out.println("city ontroller called");
		String ipAddress = orgCommonUtilityDao.getIpAddress(request);
		Timestamp creationTime = orgCommonUtilityDao.getTimeStamp();
		
		updateCity.getId();
		updateCity.getCityName();
		updateCity.getMappingCountryCity();
		updateCity.getMappingStateCity();
		updateCity.getStatus();
		updateCity.getPopulation();
		updateCity.getRemoteLocation();
		updateCity.getLocation();
		
		if(updateCity.getRemoteLocation()!=null)
		{
			updateCity.getRemoteLocation();
		}
		else
		{
			updateCity.setRemoteLocation("N");
		}
		
		updateCity.setIpAddress(ipAddress);
		updateCity.setCreationTime(creationTime);
		updateCity.setUser(12345);
		
		try
		{
			add_status=cityService.update(updateCity);
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
		
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName","Y");
		List<City> cityList = cityService.findAll();
		List<State> stateList=stateService.findAllByAscendingOrder("stateName", "Y");	
		model.addAttribute("printstateList", stateList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("cityListSize",cityList.size());
		model.addAttribute("edit_det", "No");
		model.addAttribute("countryList",countryList);	
		
		
		JSONObject json = new JSONObject();
		json.put("return_status", return_status);
		//return json;
		return new ModelAndView("place/city/city");
		}
	
	@ResponseBody
	@RequestMapping(value="/deleteCity")
	public ModelAndView deleteCity(@RequestParam int id,Model model){		
		
		List<Country> countryList = countryService.findAllByAscendingOrder("countryName","Y");
		List<City> cityList = cityService.findAll();
		List<State> stateList=stateService.findAllByAscendingOrder("stateName", "Y");	
		model.addAttribute("printstateList", stateList);
		model.addAttribute("cityList",cityList);
		model.addAttribute("cityListSize",cityList.size());
		model.addAttribute("edit_det", "No");
		model.addAttribute("countryList",countryList);	
		
		cityService.deleteById(id);
		return new ModelAndView("place/city/city");
		
	}
	
}
