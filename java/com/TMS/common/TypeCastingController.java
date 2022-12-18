package com.TMS.common;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TypeCastingController {

	//String to Integer
	public Integer stringToInteger(String stringVal){		
		Integer intVal = Integer.parseInt(stringVal);
		return intVal;		
	}
	
	//String to double
	public double stringToDouble(String stringVal) {
		Double doubleVal = Double.valueOf(stringVal);
		return doubleVal;
	}	
	
	//double to String
	public String doubleToString(Double doubleVal) {
		String stringVal = String.valueOf(doubleVal);
		return stringVal;
	}
	
	//Integer to double	
	public double intToDouble(Integer intVal) {
		double doubleVal = (double)intVal;		
		return doubleVal;		
	}
	
	//Double to Integer
	public Integer doubleToInt(Double doubleVal) {
		Integer intVal = doubleVal.intValue();
		return intVal;
	}
	
	//BigDecimal to String
	public String bigDecToString(BigDecimal bigDecVal) {
		String stringVal = bigDecVal.toString();
		return stringVal;
	}
	
	//String to BigDecimal
	public BigDecimal stringToBigDec(String stringVal) {
		BigDecimal bigDecVal = new BigDecimal(stringVal);
		return bigDecVal;		
	}
	
	//BigDeciaml to Integer
	public Integer bigDecToInteger(BigDecimal bigDecVal) {
		Integer intVal = bigDecVal.intValue();
		return intVal;
	}
	
	//String to Long
	public Long stringToLong(String stringVal) {
		Long longVal = Long.valueOf(stringVal);
		return longVal;
	}
	
	//Integer to Long
	public Long intToLong(Integer intVal) {
		Long longVal = intVal.longValue();
		return longVal;
	}
	
	//Long To Integer
	public Integer longToInt(Long longVal) {
		Integer intVal = longVal.intValue();
		return intVal;
	}
	
	//Date Conversion
	//String to Sql Date
	public Date stringToSqlDate(String stringVal) {
		Date dateVal = Date.valueOf(stringVal);
		return dateVal;
	}	
	
	//Sql Date to String Date
	public String sqlToStringDate(Date sqlDate) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");  
		String stringDate = df.format(sqlDate);  
		return stringDate;		
	}
	
	//Sql Date to Util Date
	public java.util.Date sqlToUtil(Date sqlDate) {		
		java.util.Date utilDate = new Date(sqlDate.getTime());		
		return utilDate;		
	}
	
	//String To Util date
	public java.util.Date stringToUtil(String stringDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date utilDate = sdf.parse(stringDate);		
		return utilDate;	
	}
	
	//Util Date to String Date
	public String utilToString(java.util.Date utilDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = dateFormat.format(utilDate); 
		return stringDate;		
	}
	
	//Util Date to Sql Date
	public Date utilToSql(java.util.Date utilDate) {
		 Date sqlDate = new java.sql.Date(utilDate.getTime());
		 return sqlDate;
	}
	
}
