package com.TMS.common;

import java.math.BigDecimal;



public class CurrencyConventionImpl   {

	public String indianCurrencyFormatWithDecimal(BigDecimal number){
		StringBuilder input1 = new StringBuilder();
		  input1.append(number);
		  input1=input1.reverse(); 
		  
		String[] demo=input1.toString().split("\\.");

		String global="";
		String newValue="";
		String newValue1="";
		
		String temp = demo[0];
		long[] newGuess = new long[temp.length()];
		for (int i = temp.length()-1; i>=0; i--)
		{
			newGuess[i] = temp.charAt(i) - '0';
			if(i==0){
				newValue="."+newValue+""+newGuess[i];
			}
			else{
				newValue=newValue+""+newGuess[i];
			}
			
		
		}
		global=newValue;
		String temp1 = demo[1];
		long[] newGuess1 = new long[temp1.length()];
		for (int i = temp1.length()-1; i>=0; i--)
		{
		    newGuess1[i] = temp1.charAt(i) - '0';
		    if(i>0 && i%2==0 && i==temp1.length()-1){
		    	newValue1=newValue1+""+newGuess1[i];
		    }
		    else if(i==0){
		    	newValue1=newValue1+""+newGuess1[i];
		    }
		    else if(i>0 && i%2==0){
		    	newValue1=newValue1+","+newGuess1[i];
		    }
		    else{
		    	newValue1=newValue1+""+newGuess1[i];
		    }
		}
		global=newValue1+""+global;

		return global;
	}
	
	
	
	public String indianCurrencyFormatWithoutDecimal(BigDecimal budgetTotal){
		StringBuilder input1 = new StringBuilder();
		  input1.append(budgetTotal);
		  input1=input1.reverse(); 
		  
		String[] demo=input1.toString().split("\\.");
		if(demo.length>1){

			String global="";
			String newValue="";
			String newValue1="";
			
			
			global=newValue;
			String temp1 = demo[1];
			long[] newGuess1 = new long[temp1.length()];
			for (int i = temp1.length()-1; i>=0; i--)
			{
			    newGuess1[i] = temp1.charAt(i) - '0';
			    if(i>0 && i%2==0 && i==temp1.length()-1){
			    	newValue1=newValue1+""+newGuess1[i];
			    }
			    else if(i==0){
			    	newValue1=newValue1+""+newGuess1[i];
			    }
			    else if(i>0 && i%2==0){
			    	newValue1=newValue1+","+newGuess1[i];
			    }
			    else{
			    	newValue1=newValue1+""+newGuess1[i];
			    }
			}
			global=newValue1+""+global;
			return global;
		}
		else{

			String global="";
			String newValue="";
			String newValue1="";
			
			
			global=newValue;
			String temp1 = demo[0];
			long[] newGuess1 = new long[temp1.length()];
			for (int i = temp1.length()-1; i>=0; i--)
			{
			    newGuess1[i] = temp1.charAt(i) - '0';
			    if(i>0 && i%2==0 && i==temp1.length()-1){
			    	newValue1=newValue1+""+newGuess1[i];
			    }
			    else if(i==0){
			    	newValue1=newValue1+""+newGuess1[i];
			    }
			    else if(i>0 && i%2==0){
			    	newValue1=newValue1+","+newGuess1[i];
			    }
			    else{
			    	newValue1=newValue1+""+newGuess1[i];
			    }
			}
			global=newValue1+""+global;
			return global;
		}
		
	}
	
	
	public String getSymbols(String currency){
		
		if(currency=="INR"){
	
		}
		
		return null;
	}

}