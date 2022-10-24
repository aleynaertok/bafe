package com.bafe.service;

import com.bafe.dto.AccountDto;
import com.bafe.model.Account;

public interface IAccountService {

    void createAccount(AccountDto accountDto);
    void checkMailExist(AccountDto accountDto);
    void updateMail(AccountDto accountDto, Long id);
    AccountDto updatePassword(AccountDto accountDto, Long id);
    void deleteAccount(Long id);
    Account getAccountById(Long id);

}
