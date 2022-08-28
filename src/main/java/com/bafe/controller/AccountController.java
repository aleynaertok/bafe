package com.bafe.controller;

import com.bafe.dto.AccountDto;
import com.bafe.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class AccountController {

    //Post bir şeyleri body ile çağırmak için ya da create işlemleri.
    //Put güncelleme işlemleri için.
    //Deletemapping direkt delete işlemleri.
    //Getmapping veri almak için

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestBody AccountDto accountDto) {

        accountService.createAccount(accountDto);
        return ResponseEntity.ok().build();




    }




    @PutMapping("/updateMail/{id}")
    public ResponseEntity<AccountDto> updateMail(@PathVariable Long id, @RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.updateMail(accountDto, id));


    }


    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<AccountDto> updatePassword(@PathVariable Long id, @RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.updatePassword(accountDto, id));


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();


    }


}
