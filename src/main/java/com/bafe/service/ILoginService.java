package com.bafe.service;

import com.bafe.dto.AccountDto;
import com.bafe.dto.LoginResultDto;

public interface ILoginService {

    LoginResultDto login(AccountDto accountDto);
}
