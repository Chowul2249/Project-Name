package com.TMS.common;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
@Service
public interface OrgCommonUtilityDao {

	public Timestamp getTimeStamp();
	
	public java.sql.Date getCurrentDate();

	public String getIpAddress(HttpServletRequest request);

	public int getagebyDOB(java.util.Date date);

	public int getagebyDOBPayMonth(Date d2, LocalDate payrollMntStrDate);
		

}
