package com.Bank.Service.Impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bank.Dto.AccountInfo;
import com.Bank.Dto.BankResponse;
import com.Bank.Dto.EnquiryRequest;
import com.Bank.Dto.UserRequest;
import com.Bank.Models.User;
import com.Bank.Repo.UserRepo;
import com.Bank.Service.UserService;
import com.Bank.Utils.AccountUtils;

@Service
public class UserServiceImpl implements UserService{

	@Autowired UserRepo userRepo;

	@Override
	public BankResponse createAccount(UserRequest userRequest) {
		if(userRepo.existsByEmail(userRequest.getEmail()))
		{
			return BankResponse.builder()
					.responceCode(AccountUtils.ACCOUNT_EXISTED_CODE)
					.responseMessage(AccountUtils.ACCOUNT_EXISTED_MESSAGE)
					.accountInfo(null)
					.build();
		}
		
		User newUser=User.builder().
				firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.otherName(userRequest.getOtherName())
				.gender(userRequest.getGender())
				.address(userRequest.getAddress())
				.stateOfOrigin(userRequest.getStateOfOrigin())
				.accountNumber(AccountUtils.generateAccountNumber())
				.accountBalance(BigDecimal.ZERO)
				.email(userRequest.getEmail())
				.phoneNumber(userRequest.getPhoneNumber())
				.alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
				.status("ACTIVE")
				.build();
		
		User savedUser=userRepo.save(newUser);
		return BankResponse.builder()
				.responceCode(AccountUtils.ACCOUNT_CREATION_SUCCESS_CODE)
				.responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
				.accountInfo(
						AccountInfo.builder()
						.accountBalance(savedUser.getAccountBalance())
						.accountNumber(savedUser.getAccountNumber())
						.accountName(savedUser.getFirstName()+" "+ savedUser.getLastName()+" "+savedUser.getOtherName())
						.build()
						)
				.build();
	}

	@Override
	public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
	Boolean isAccountExist=userRepo.existsByAccountNumber(enquiryRequest.getAccountNumber());
		if (!isAccountExist) {
			return BankResponse.builder()
					.responceCode(AccountUtils.ACCOUNT_NOT_EXISTED_CODE)
					.responseMessage(AccountUtils.ACCOUNT_NOT_EXISTED_MESSAGE)
					.accountInfo(null)
					.build();
		}
		User foundUser=userRepo.findByAccountNumber(enquiryRequest.getAccountNumber());
		return BankResponse.builder()
				.responceCode(AccountUtils.ACCOUNT_FOUND_CODE)
				.responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
				.accountInfo(AccountInfo.builder()
						.accountName(foundUser.getFirstName()+ " "+foundUser.getLastName())
						.accountNumber(foundUser.getAccountNumber())
						.accountBalance(foundUser.getAccountBalance())
						.build())
				.build();
	}

	@Override
	public String nameEnquiry(EnquiryRequest enquiryRequest) {
		Boolean isAccountExist=userRepo.existsByAccountNumber(enquiryRequest.getAccountNumber());
		if (!isAccountExist) {
			return AccountUtils.ACCOUNT_NOT_EXISTED_MESSAGE;
		}
		User foundUser=userRepo.findByAccountNumber(enquiryRequest.getAccountNumber());
		return foundUser.getFirstName()+ " "+foundUser.getLastName();
	}
	
	

}
