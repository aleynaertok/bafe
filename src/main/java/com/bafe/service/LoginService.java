package com.bafe.service;


import com.bafe.dto.AccountDto;
import com.bafe.dto.LoginResultDto;
import com.bafe.model.Account;
import com.bafe.repository.LoginRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }


    public LoginResultDto login(AccountDto accountDto) {

        Optional<Account> accountOptional = loginRepository.findAccountByMailAndPassword(accountDto.getMail(), accountDto.getPassword());

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();


            return new LoginResultDto(account);


        } else {
            throw new RuntimeException("Kullanıcı adı veya şifre hatalı");
        }


    }

}
