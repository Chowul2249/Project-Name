package com.TMS.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.TMS.common.OrgCommonUtilityDao;

@Repository
public class OrgCommonUtilityDaoImpl implements OrgCommonUtilityDao{

	@Override
	public Timestamp getTimeStamp() {
		// TODO Auto-generated method stub
		java.util.Date date = new java.util.Date();		
		Long time =date.getTime();		
		Timestamp timeStamp = new Timestamp(time);
		return timeStamp;
		
	}

	@Override
	public Date getCurrentDate() {
         java.util.Date date = new java.util.Date();	
		
		java.sql.Date d1 = new Date(date.getTime());
		
		return d1;
	}

	@Override
	public String getIpAddress(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		 if (ipAddress == null) {ipAddress = request.getRemoteAddr();}
		return ipAddress;
	}

	@Override
	public int getagebyDOB(java.util.Date date) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  LocalDate birthday = LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
			LocalDate now = LocalDate.now();
			Period p = Period.between(birthday, now);
		return p.getYears();
	}

	
	@Override
	public int getagebyDOBPayMonth(java.util.Date d2,
			LocalDate payrollMntStrDate) {

		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		  cal.setTime(d2);
		  LocalDate birthday = LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH));
			Period p = Period.between(birthday, payrollMntStrDate);
		return p.getYears();
	}
	
}
