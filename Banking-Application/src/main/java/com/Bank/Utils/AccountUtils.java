package com.Bank.Utils;

import java.time.Year;

public class AccountUtils {
	
	public static final String ACCOUNT_EXISTED_CODE="001";
	public static final String ACCOUNT_EXISTED_MESSAGE="This user already has an account created!";

	public static final String ACCOUNT_CREATION_SUCCESS_CODE="002";
	public static final String ACCOUNT_CREATION_MESSAGE="Account has been successfully created";

	
	
	public static String generateAccountNumber()
	{
		Year currentYear=Year.now();
		int min=100000;
		int max=999999;
		
		int randomNumber=(int)Math.floor(Math.random()*(max-min +1));
		
		String year=String.valueOf(currentYear);
		String number=String.valueOf(randomNumber);
		StringBuilder accountNumber=new StringBuilder();
		return accountNumber.append(year).append(number).toString();
	}
}
