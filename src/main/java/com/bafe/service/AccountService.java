package com.bafe.service;


import com.bafe.dto.AccountDto;
import com.bafe.exception.IsEmptyException;
import com.bafe.model.Account;
import com.bafe.repository.AccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }


    public void createAccount(AccountDto accountDto) {

        checkMailExist(accountDto);


        Account account = new Account();
        account.setMail(accountDto.getMail());
        account.setPassword(accountDto.getPassword());
        account.setAdmin(false);
        account.setActivity(true);

        accountRepository.save(account);


    }

    public void checkMailExist(AccountDto accountDto) {
        Optional<Account> accountOptional = accountRepository.findAccountByMail(accountDto.getMail());
        if (accountOptional.isPresent()) {
            throw new IsEmptyException("Bu mail zaten kayıtlı.");
        }
    }

    public void updateMail(AccountDto accountDto, Long id) {

        Account account = getAccountById(id);
        account.setMail(accountDto.getMail());


        accountRepository.save(account);
    }

    public AccountDto updatePassword(AccountDto accountDto, Long id) {
        Account account = getAccountById(id);

        account.setPassword(accountDto.getPassword());


        return new AccountDto(accountRepository.save(account));
    }

    public void deleteAccount(Long id) {

        Account account = getAccountById(id);


        account.setActivity(false);
        accountRepository.save(account);


    }

    public Account getAccountById(Long id) {

        return accountRepository.findById(id).orElseThrow(() -> new IsEmptyException("Kullanıcı bulunamadı."));


    }


}
