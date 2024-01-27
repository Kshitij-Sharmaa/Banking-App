package com.Bank.Service;

import com.Bank.Dto.BankResponse;
import com.Bank.Dto.UserRequest;

public interface UserService {

	BankResponse createAccount(UserRequest userRequest);

}
