package com.bafe.service;


import com.bafe.dto.AccountDto;
import com.bafe.exception.IsEmptyException;
import com.bafe.model.Account;
import com.bafe.repository.AccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;


    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    public void validateAccount(AccountDto accountDto) {

        //StringUtils pom.xml ekle


        if (StringUtils.isEmpty(accountDto.getMail())) {

            throw new IsEmptyException("Mail uygun değildir.");
        }


    }

    public void createAccount(AccountDto accountDto) {
        validateAccount(accountDto);

        checkMailExist(accountDto);



        Account account = new Account();
        account.setMail(accountDto.getMail());
        account.setPassword(accountDto.getPassword());
        account.setAdmin(false);
        account.setActivity(true);

        accountRepository.save(account);




    }

    private void checkMailExist(AccountDto accountDto) {
        Optional<Account> accountOptional = accountRepository.findAccountByMail(accountDto.getMail());
        if (accountOptional.isPresent()) {
            throw new IsEmptyException("Bu mail zaten kayıtlı.");
        }
    }

    public AccountDto updateMail(AccountDto accountDto, Long id) {

        Account account = getAccountById(id);
        account.setMail(accountDto.getMail());


        return new AccountDto(accountRepository.save(account));
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


       /* Optional<Account> accountOptional = accountRepository.findById(id);


        if (accountOptional.isPresent()) {
            return accountOptional.get();

        } else {
            throw new IsEmptyException("Kullanıcı bulunamadı.");
        }

        */


    }


}
